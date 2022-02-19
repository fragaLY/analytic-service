package by.vk.betting.api.analytic.service.metric;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import by.vk.betting.api.analytic.service.agregator.TeamAnalyticAggregator;
import by.vk.betting.api.analytic.service.counter.PerGameReceiveCounter;
import by.vk.betting.api.analytic.service.predicate.ExposedResponsePredicate;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public record LessReceivePerGameCalculator(TeamAnalyticAggregator aggregator,
                                           PerGameReceiveCounter counter,
                                           ExposedResponsePredicate responsePredicate) implements Calculable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LessReceivePerGameCalculator.class);

    public ResultHolder calculate(Flux<ExposedResponse> dataset) {
        LOGGER.info("[METRICS] Calculating less received per game team(s)");
        var result = dataset
                .toStream()
                .filter(responsePredicate)
                .flatMap(aggregator)
                .collect(Collectors.groupingBy(TeamAnalyticResult::teamKey))
                .entrySet()
                .stream()
                .map(counter)
                .min(Comparator.comparingDouble(ResultHolder::amount))
                .orElseThrow(() -> new NotFoundException("Less received per game team not found"));
        LOGGER.info("[METRICS] Less received per game team(s) are [{}]", result);
        return result;
    }
}
