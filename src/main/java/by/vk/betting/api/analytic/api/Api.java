package by.vk.betting.api.analytic.api;

import by.vk.betting.api.analytic.dto.analytic.Metric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/teams")
public interface Api {

  @GetMapping("/aggregate")
  @ResponseStatus(OK)
  ResponseEntity<Map<String, Metric>> get();
}
