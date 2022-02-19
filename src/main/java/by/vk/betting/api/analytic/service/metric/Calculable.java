package by.vk.betting.api.analytic.service.metric;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import reactor.core.publisher.Flux;

public interface Calculable {
  Metric calculate(Flux<ExposedResponse> dataset);

  String getMetricName();
}
