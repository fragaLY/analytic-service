package by.vk.betting.api.analytic.service;

import by.vk.betting.api.analytic.dto.result.Response;
import by.vk.betting.api.analytic.service.metric.LessReceivePerGameCalculator;
import by.vk.betting.api.analytic.service.metric.MostScoredPerGameCalculator;
import by.vk.betting.api.analytic.service.metric.MostWinCalculator;
import by.vk.betting.api.dataset.provider.DatasetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record AnalyticService(DatasetProvider provider,
                              MostWinCalculator mostWinCalculator,
                              MostScoredPerGameCalculator mostScoredPerGameCalculator,
                              LessReceivePerGameCalculator lessReceivePerGameCalculator) {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticService.class);

    public Response get() {
       LOGGER.info("[ANALYTIC] Calculating aggregated results");
       var dataset = provider.provide();
       var mostWinTeam = mostWinCalculator.calculate(dataset);
       var mostScorePerGameTeam = mostScoredPerGameCalculator.calculate(dataset);
       var lessReceivePerGameTeam = lessReceivePerGameCalculator.calculate(dataset);
       return new Response(mostWinTeam, mostScorePerGameTeam, lessReceivePerGameTeam);
    }
}
