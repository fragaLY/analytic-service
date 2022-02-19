package by.vk.betting.api.analytic.service.metric;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import by.vk.betting.api.analytic.service.agregator.TeamAnalyticAggregator;
import by.vk.betting.api.analytic.service.counter.PerGameScoreCounter;
import by.vk.betting.api.analytic.service.predicate.ExposedResponsePredicate;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public record MostScoredPerGameCalculator(TeamAnalyticAggregator aggregator,
                                          PerGameScoreCounter counter,
                                          ExposedResponsePredicate responsePredicate) implements Calculable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MostScoredPerGameCalculator.class);

    public ResultHolder calculate(Flux<ExposedResponse> dataset) {
        LOGGER.info("[MOST-SCORE-CALCULATOR] Calculating most scored per game team.");
        return dataset
                .toStream()
                .filter(responsePredicate)
                .flatMap(aggregator)
                .collect(Collectors.groupingBy(TeamAnalyticResult::teamKey))
                .entrySet()
                .stream()
                .map(counter)
                .max(Comparator.comparingDouble(ResultHolder::amount))
                .orElseThrow(() -> new NotFoundException("Most scored per game team not found"));
    }
}
