package by.vk.betting.api.analytic.service.counter;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class WinCounter
    implements Function<Map.Entry<String, List<TeamAnalyticResult>>, ResultHolder>, Countable {

  private static final Logger LOGGER = LoggerFactory.getLogger(WinCounter.class);

  @Override
  public ResultHolder apply(Map.Entry<String, List<TeamAnalyticResult>> entry) {
    LOGGER.debug("[METRICS] Calculating wins metric for [{}]", entry.getKey());
    var analytic = entry.getValue();
    var amount =
        analytic.stream().mapToInt(tar -> tar.teamScore() > tar.opponentScore() ? ONE : ZERO).sum();
    var name = analytic.get(ZERO).teamName();
    var result = new ResultHolder(name, amount);
    LOGGER.debug("[METRICS] Wins metric for [{}] is [{}]", entry.getKey(), result);
    return result;
  }
}
