package by.vk.betting.api.configuration.exception;

import org.springframework.http.HttpStatus;

public record ExceptionInformation(HttpStatus status, Integer code, String message) {
}
