package by.vk.betting.api.analytic.dto.analytic;

public record TeamAnalyticResult(String teamKey,
                                 String teamName,
                                 String opponentName,
                                 int teamScore,
                                 int opponentScore,
                                 String tournament,
                                 String city,
                                 String country) {
}
