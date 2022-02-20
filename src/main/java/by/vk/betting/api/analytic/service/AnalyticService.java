package by.vk.betting.api.analytic.service;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import by.vk.betting.api.analytic.service.metric.Calculable;
import by.vk.betting.api.dataset.provider.DatasetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public record AnalyticService(DatasetProvider provider, List<Calculable> metricsCalculators) {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticService.class);

    public Map<String, Metric> get() {
       LOGGER.info("[ANALYTIC] Calculating aggregated results");
       var dataset = provider.provide();
       return metricsCalculators
               .stream()
               .map(it -> Map.of(it.getMetricName(), it.calculate(dataset)))
               .flatMap(metrics -> metrics.entrySet().stream())
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
