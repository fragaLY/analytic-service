package by.vk.betting.api.analytic.service.metric;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.analytic.service.agregator.TeamAnalyticAggregator;
import by.vk.betting.api.analytic.service.counter.WinCounter;
import by.vk.betting.api.analytic.service.predicate.ExposedResponsePredicate;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public record MostWinCalculator(TeamAnalyticAggregator aggregator,
                                WinCounter counter,
                                ExposedResponsePredicate responsePredicate) implements Calculable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MostWinCalculator.class);

    @Override
    public Metric calculate(Flux<ExposedResponse> dataset) {
        LOGGER.info("[METRICS] Calculating most win team(s).");
        var result = dataset
                .toStream()
                .parallel()
                .filter(responsePredicate)
                .flatMap(aggregator)
                .collect(Collectors.groupingBy(TeamAnalyticResult::teamKey))
                .entrySet()
                .parallelStream()
                .map(counter)
                .collect(Collectors.toMap(Metric::amount, Metric::team, (firstTeam, secondTeam) -> String.format("%s, %s", firstTeam, secondTeam)))
                .entrySet()
                .parallelStream()
                .max(Comparator.comparingInt(entry -> entry.getKey().toBigIntegerExact().intValueExact()))
                .map(it -> new Metric(it.getValue(), it.getKey()))
                .orElseThrow(() -> new NotFoundException("Most win games team not found"));
        LOGGER.info("[METRICS] Most win team(s) are [{}]", result);
        return result;
    }

    @Override
    public String getMetricName() {
        return "mostWin";
    }
}
