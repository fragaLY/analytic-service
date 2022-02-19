package by.vk.betting.api.configuration.client;

import by.vk.betting.api.analytic.service.AnalyticService;
import by.vk.betting.api.configuration.client.properties.WebClientProperties;
import by.vk.betting.api.configuration.exception.types.UnexpectedException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.util.retry.RetryBackoffSpec;
import reactor.util.retry.RetrySpec;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticService.class);

  @Bean
  @ConditionalOnProperty(name = "betting.webclient.enabled", matchIfMissing = true)
  public WebClient webClient(WebClientProperties properties) {

    var provider =
        ConnectionProvider.builder("custom")
            .maxConnections(10)
            .maxIdleTime(Duration.ofSeconds(20))
            .maxLifeTime(Duration.ofSeconds(60))
            .pendingAcquireTimeout(Duration.ofSeconds(60))
            .evictInBackground(Duration.ofSeconds(120))
            .build();

    final var httpClient =
        HttpClient.create(provider)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.timeout())
            .doOnConnected(
                connection -> {
                  connection.addHandlerLast(
                      new ReadTimeoutHandler(properties.timeout(), TimeUnit.MILLISECONDS));
                  connection.addHandlerLast(
                      new WriteTimeoutHandler(properties.timeout(), TimeUnit.MILLISECONDS));
                });

    provider.disposeLater().block();

    return WebClient.builder()
        .baseUrl(properties.baseUrl())
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .build();
  }

  @Bean
  public RetryBackoffSpec retrySpec(WebClientProperties properties) {
    return RetrySpec.backoff(properties.maxAttempts(), Duration.ofSeconds(properties.duration()))
        .doBeforeRetry(
            signal ->
                LOGGER.warn(
                    String.format(
                        "Retrying to retrieve dataset. Retry signal [%d]. Cause [%s]",
                        signal.totalRetries(), signal.failure().getMessage())))
        .filter(throwable -> throwable instanceof Exception)
        .onRetryExhaustedThrow(
            (retryBackoffSpec, retrySignal) -> {
              throw new UnexpectedException(
                  String.format(
                      "External Service failed to process after max [%s] retries",
                      properties.maxAttempts()));
            });
  }
}
