package by.vk.betting.api.analytic.convertor;

import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResponseToTeamAnalyticConvertor {

  private static final String WHITE_SPACES_REGEX_VALUE = "\\s";

  public TeamAnalyticResult convert(ExposedResponse response) {
    return new TeamAnalyticResult(
        response
            .homeTeam()
            .toLowerCase(Locale.ROOT)
            .replaceAll(WHITE_SPACES_REGEX_VALUE, Strings.EMPTY),
        response.homeTeam(),
        response.awayTeam(),
        response.homeScore(),
        response.awayScore(),
        response.tournament(),
        response.city(),
        response.country());
  }

  public TeamAnalyticResult revers(ExposedResponse response) {
    return new TeamAnalyticResult(
        response
            .awayTeam()
            .toLowerCase(Locale.ROOT)
            .replaceAll(WHITE_SPACES_REGEX_VALUE, Strings.EMPTY),
        response.awayTeam(),
        response.homeTeam(),
        response.awayScore(),
        response.homeScore(),
        response.tournament(),
        response.city(),
        response.country());
  }
}
