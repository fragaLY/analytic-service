package by.vk.betting.api.dataset.provider;

import by.vk.betting.api.analytic.dto.exposed.ExposedResponse;
import by.vk.betting.api.configuration.client.properties.WebClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public record DatasetProvider(WebClientProperties properties) {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatasetProvider.class);

    public List<ExposedResponse> provide() {
        LOGGER.info("[DATASET PROVIDER] Providing datasets for segments [{}]", properties.segments());
        var template = new RestTemplate();
        return properties
                .segments()
                .parallelStream()
                .map(segment -> template.getForEntity(URI.create(properties.baseUrl().concat(segment)), ExposedResponse[].class))
                .flatMap(response -> Arrays.stream(Objects.requireNonNull(response.getBody()))).collect(Collectors.toList());
    }
}
