package by.vk.betting.api.analytic.service.function;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class TeamAnalyticAggregator implements Function<ExposedResponse, Stream<TeamAnalyticResult>> {

  @Override
  public Stream<TeamAnalyticResult> apply(ExposedResponse response) {
    var homeTeam =
        new TeamAnalyticResult(
            response.homeTeam(),
            response.awayTeam(),
            response.homeScore(),
            response.awayScore(),
            response.tournament(),
            response.city(),
            response.country());

    var awayTeam =
        new TeamAnalyticResult(
            response.awayTeam(),
            response.homeTeam(),
            response.awayScore(),
            response.homeScore(),
            response.tournament(),
            response.city(),
            response.country());
    return Stream.of(homeTeam, awayTeam);
  }
}
