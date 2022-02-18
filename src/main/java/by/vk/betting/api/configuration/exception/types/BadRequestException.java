package by.vk.betting.api.configuration.exception.types;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
