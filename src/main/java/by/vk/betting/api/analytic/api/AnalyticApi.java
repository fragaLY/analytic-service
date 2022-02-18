package by.vk.betting.api.analytic.api;

import by.vk.betting.api.analytic.service.AnalyticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticApi implements Api {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticApi.class);

    private final AnalyticService service;

    public AnalyticApi(AnalyticService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> get() {
        LOGGER.info("[ANALYTIC] Retrieving analytic for historical data");
        service.get();
        return ResponseEntity.ok().build();
    }

}
