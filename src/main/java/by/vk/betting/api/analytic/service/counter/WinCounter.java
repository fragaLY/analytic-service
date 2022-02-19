package by.vk.betting.api.analytic.service.counter;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class WinCounter
    implements Function<Map.Entry<String, List<TeamAnalyticResult>>, ResultHolder>, Countable {

  @Override
  public ResultHolder apply(Map.Entry<String, List<TeamAnalyticResult>> entry) {
    var analytic = entry.getValue();
    var amount =
        analytic.stream().mapToInt(tar -> tar.teamScore() > tar.opponentScore() ? ONE : ZERO).sum();
    var name = analytic.get(ZERO).teamName();
    return new ResultHolder(name, amount);
  }
}
