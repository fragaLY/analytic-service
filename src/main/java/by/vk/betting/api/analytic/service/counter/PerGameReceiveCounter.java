package by.vk.betting.api.analytic.service.counter;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class PerGameReceiveCounter
    implements Function<Map.Entry<String, List<TeamAnalyticResult>>, Metric>, Countable {

  private static final Logger LOGGER = LoggerFactory.getLogger(PerGameReceiveCounter.class);

  @Override
  public Metric apply(Map.Entry<String, List<TeamAnalyticResult>> entry) {
    LOGGER.debug("[METRICS] Calculating per game received metric for [{}]", entry.getKey());
    var analytics = entry.getValue();
    var received = analytics.stream().mapToInt(TeamAnalyticResult::opponentScore).sum();
    var games = analytics.size();
    var name = analytics.get(ZERO).teamName();
    var amount = received == ZERO ? ZERO : (double) received / games;
    var metric = new Metric(name, BigDecimal.valueOf(amount));
    LOGGER.debug("[METRICS] Per game received metric for [{}] is [{}]", entry.getKey(), metric);
    return metric;
  }
}
