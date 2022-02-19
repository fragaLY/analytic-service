package by.vk.betting.api.analytic.service;

import by.vk.betting.api.analytic.dto.result.Response;
import by.vk.betting.api.dataset.provider.DatasetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record AnalyticService(DatasetProvider provider,
                              MostWinCalculator mostWinCalculator,
                              MostScoredCalculator mostScoredCalculator,
                              LessReceiveCalculator lessReceiveCalculator) {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticService.class);

    public Response get() {

        LOGGER.info("[ANALYTIC] Calculating aggregated results");
        var dataset = provider.provide();
        var mostWinTeam = mostWinCalculator.calculate(dataset);
        var mostScoreTeam = mostScoredCalculator.calculate(dataset);
        var lessReceiveTeam = lessReceiveCalculator.calculate(dataset);

       LOGGER.info("Most win [{}]", mostWinTeam);
       LOGGER.info("Most score [{}]", mostScoreTeam);
       LOGGER.info("Less receive [{}]", lessReceiveTeam);
       return new Response(mostWinTeam, mostScoreTeam, lessReceiveTeam);
    }
}
