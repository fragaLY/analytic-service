package by.vk.betting.api.analytic.service.metric;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;

import java.util.List;

public interface Calculable {
  Metric calculate(List<ExposedResponse> dataset);

  String getMetricName();
}
