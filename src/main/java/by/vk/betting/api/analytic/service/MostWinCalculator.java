package by.vk.betting.api.analytic.service;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import by.vk.betting.api.analytic.service.function.TeamAnalyticAggregator;
import by.vk.betting.api.analytic.service.function.WinCounter;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public record MostWinCalculator(TeamAnalyticAggregator aggregator, WinCounter counter) {

    private static final Logger LOGGER = LoggerFactory.getLogger(MostWinCalculator.class);

    public ResultHolder calculate(Flux<ExposedResponse> dataset) {
        LOGGER.info("[MOST-WIN-CALCULATOR] Calculating most win team.");
        return dataset
                .toStream()
                .flatMap(aggregator)
                .collect(Collectors.groupingBy(TeamAnalyticResult::team))
                .entrySet()
                .stream()
                .map(counter)
                .max(Comparator.comparingInt(ResultHolder::amount))
                .orElseThrow(() -> new NotFoundException("Winner not found"));
    }
}
