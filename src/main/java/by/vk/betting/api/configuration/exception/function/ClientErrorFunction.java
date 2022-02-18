package by.vk.betting.api.configuration.exception.function;

import by.vk.betting.api.configuration.exception.types.UnexpectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class ClientErrorFunction implements Function<ClientResponse, Mono<? extends Throwable>> {

  @Override
  public Mono<? extends Throwable> apply(ClientResponse response) {
    return Mono.error(
        new UnexpectedException(
            String.format(
                "Unexpected exception with status code [%s]. Please, try again later.",
                response.statusCode())));
  }
}
