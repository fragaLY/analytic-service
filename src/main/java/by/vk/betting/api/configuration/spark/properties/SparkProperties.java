package by.vk.betting.api.configuration.spark.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "betting.spark")
public record SparkProperties(String appName) {
}