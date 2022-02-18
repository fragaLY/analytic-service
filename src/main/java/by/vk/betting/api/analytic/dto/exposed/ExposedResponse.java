package by.vk.betting.api.analytic.dto.exposed;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExposedResponse(String homeTeam, String awayTeam, int homeScore, int awayScore, String errorCode) {
}
