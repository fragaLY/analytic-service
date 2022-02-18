package by.vk.betting.api;

import by.vk.betting.api.analytic.service.AnalyticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.info.BuildProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ServiceApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticService.class);

  public static void main(String[] args) {
    final var context = SpringApplication.run(ServiceApplication.class, args);
    final var properties = context.getBean(BuildProperties.class);
    LOGGER.info("[SERVICE] Application version {}", properties.getVersion());
  }
}
