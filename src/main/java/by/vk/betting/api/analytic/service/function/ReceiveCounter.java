package by.vk.betting.api.analytic.service.function;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.result.ResultHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class ReceiveCounter
    implements Function<Map.Entry<String, List<TeamAnalyticResult>>, ResultHolder> {

  @Override
  public ResultHolder apply(Map.Entry<String, List<TeamAnalyticResult>> entry) {
    var amount = entry.getValue().stream().mapToInt(TeamAnalyticResult::opponentScore).sum();
    return new ResultHolder(entry.getKey(), amount);
  }
}
