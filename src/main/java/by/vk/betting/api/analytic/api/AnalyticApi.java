package by.vk.betting.api.analytic.api;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import by.vk.betting.api.analytic.service.AnalyticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnalyticApi implements Api {

  private final AnalyticService service;

  public AnalyticApi(AnalyticService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<Map<String, Metric>> get() {
    return ResponseEntity.ok(service.get());
  }
}
