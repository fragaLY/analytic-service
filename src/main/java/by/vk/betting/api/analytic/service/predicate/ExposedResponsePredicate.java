package by.vk.betting.api.analytic.service.predicate;

import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Predicate;

@Component
public class ExposedResponsePredicate implements Predicate<ExposedResponse> {

  @Override
  public boolean test(ExposedResponse response) {
    return Objects.nonNull(response)
        && Strings.isNotBlank(response.homeTeam())
        && Strings.isNotBlank(response.awayTeam());
  }
}
