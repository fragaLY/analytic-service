package by.vk.betting.api.analytic.service;

import by.vk.betting.api.dataset.provider.DatasetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record AnalyticService(DatasetProvider provider, MostWinCalculator mostWinCalculator) {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticService.class);

    public void get() {

        var dataset = provider.provide();
        var mostWinTeam = mostWinCalculator.calculate(dataset);

       LOGGER.info("Most win [{}]", mostWinTeam);
    }
}
