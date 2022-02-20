package by.vk.betting.api.analytic.service.agregator;

import by.vk.betting.api.analytic.convertor.ResponseToTeamAnalyticConvertor;
import by.vk.betting.api.analytic.dto.analytic.TeamAnalyticResult;
import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Stream;

@Component
public record TeamAnalyticAggregator(ResponseToTeamAnalyticConvertor convertor)
        implements Function<ExposedResponse, Stream<TeamAnalyticResult>> {

  @Override
  public Stream<TeamAnalyticResult> apply(ExposedResponse response) {
    return Stream.of(convertor.convert(response), convertor.revers(response));
  }
}
