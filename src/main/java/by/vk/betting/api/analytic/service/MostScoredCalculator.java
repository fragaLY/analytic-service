package by.vk.betting.api.analytic.service;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import by.vk.betting.api.analytic.service.function.ScoreCounter;
import by.vk.betting.api.analytic.service.function.TeamAnalyticAggregator;
import by.vk.betting.api.analytic.service.function.WinCounter;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public record MostScoredCalculator(TeamAnalyticAggregator aggregator, ScoreCounter counter) {

    public ResultHolder calculate(Flux<ExposedResponse> dataset) {
        return dataset
                .toStream()
                .flatMap(aggregator)
                .collect(Collectors.groupingBy(TeamAnalyticResult::team))
                .entrySet()
                .stream()
                .map(counter)
                .max(Comparator.comparingInt(ResultHolder::getAmount))
                .orElseThrow(() -> new NotFoundException("Winner not found"));
    }
}
