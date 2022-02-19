package by.vk.betting.api.analytic.api;

import by.vk.betting.api.analytic.dto.result.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/teams")
@Validated
public interface Api {

  @GetMapping("/aggregate")
  @ResponseStatus(OK)
  ResponseEntity<Response> get();
}
