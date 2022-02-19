package by.vk.betting.api.dataset.provider;

import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.configuration.client.properties.WebClientProperties;
import by.vk.betting.api.configuration.exception.function.ClientErrorFunction;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.RetryBackoffSpec;

@Component
public record DatasetProvider(WebClientProperties properties,
                              WebClient client,
                              RetryBackoffSpec retrySpec,
                              ClientErrorFunction clientErrorFunction) {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatasetProvider.class);

    public Flux<ExposedResponse> provide() {
        LOGGER.info("[DATASET PROVIDER] Providing datasets for segments [{}]", properties.segments());
        return properties.segments().parallelStream()
                .map(segment -> client.get().uri(String.join(Strings.EMPTY, segment)))
                .map(spec -> spec.retrieve().onStatus(HttpStatus::isError, clientErrorFunction))
                .map(body -> body.bodyToFlux(ExposedResponse.class).retryWhen(retrySpec))
                .reduce(Flux::mergeWith).orElseThrow(()-> new NotFoundException("No datasets found."));
    }
}
