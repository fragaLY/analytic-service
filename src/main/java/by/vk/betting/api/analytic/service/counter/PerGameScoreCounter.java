package by.vk.betting.api.analytic.service.counter;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class PerGameScoreCounter
    implements Function<Map.Entry<String, List<TeamAnalyticResult>>, ResultHolder>, Countable {

  @Override
  public ResultHolder apply(Map.Entry<String, List<TeamAnalyticResult>> entry) {
    var analytics = entry.getValue();
    var scored = analytics.stream().mapToInt(TeamAnalyticResult::teamScore).sum();
    var games = analytics.size();
    var name = analytics.get(ZERO).teamName();
    var amount = scored == ZERO ? ZERO : (double) scored / games;
    return new ResultHolder(name, amount);
  }
}
