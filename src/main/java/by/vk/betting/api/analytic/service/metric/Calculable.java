package by.vk.betting.api.analytic.service.metric;

import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import reactor.core.publisher.Flux;

public interface Calculable {
  ResultHolder calculate(Flux<ExposedResponse> response);
}
