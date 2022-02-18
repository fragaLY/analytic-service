package by.vk.betting.api.configuration.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("betting.webclient")
public record WebClientProperties(int timeout, String baseUrl, List<String> segments, int maxAttempts, long duration) {
}
