package by.vk.betting.api.analytic.dto.exposed;

public record ExposedResponse(String homeTeam,
                              String awayTeam,
                              int homeScore,
                              int awayScore,
                              String tournament,
                              String city,
                              String country) {
}
