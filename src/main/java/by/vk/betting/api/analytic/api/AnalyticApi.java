package by.vk.betting.api.analytic.api;

import by.vk.betting.api.analytic.dto.result.Response;
import by.vk.betting.api.analytic.service.AnalyticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticApi implements Api {

  private final AnalyticService service;

  public AnalyticApi(AnalyticService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<Response> get() {
    return ResponseEntity.ok(service.get());
  }
}
