package by.vk.betting.api.configuration.exception.handler;

import by.vk.betting.api.configuration.exception.ExceptionInformation;
import by.vk.betting.api.configuration.exception.types.BadRequestException;
import by.vk.betting.api.configuration.exception.types.NotFoundException;
import by.vk.betting.api.configuration.exception.types.UnexpectedException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  @ResponseStatus(value = BAD_REQUEST)
  public ExceptionInformation handleIllegalArgumentExcception(IllegalArgumentException exception) {
    return new ExceptionInformation(BAD_REQUEST, BAD_REQUEST.value(), exception.getMessage());
  }

  @ExceptionHandler(value = {NotFoundException.class})
  @ResponseStatus(value = NOT_FOUND)
  public ExceptionInformation handleNotFoundException(NotFoundException exception) {
    return new ExceptionInformation(NOT_FOUND, NOT_FOUND.value(), exception.getMessage());
  }

  @ExceptionHandler(value = {UnexpectedException.class})
  @ResponseStatus(value = INTERNAL_SERVER_ERROR)
  public ExceptionInformation handleUnexpectedException(UnexpectedException exception) {
    return new ExceptionInformation(
        INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.value(), exception.getMessage());
  }

  @ExceptionHandler(value = {BadRequestException.class})
  @ResponseStatus(value = BAD_REQUEST)
  public ExceptionInformation handleBadRequestException(BadRequestException exception) {
    return new ExceptionInformation(BAD_REQUEST, BAD_REQUEST.value(), exception.getMessage());
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  @ResponseStatus(value = BAD_REQUEST)
  public ExceptionInformation handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    var message =
        exception.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));
    return new ExceptionInformation(BAD_REQUEST, BAD_REQUEST.value(), message);
  }

  @ExceptionHandler(value = {HttpMessageNotReadableException.class})
  @ResponseStatus(value = BAD_REQUEST)
  public ExceptionInformation handleHttpMessageNotReadableException(
      HttpMessageNotReadableException exception) {
    return new ExceptionInformation(BAD_REQUEST, BAD_REQUEST.value(), exception.getMessage());
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  @ResponseStatus(value = BAD_REQUEST)
  public ExceptionInformation handleConstraintViolationException(
      ConstraintViolationException exception) {
    var message =
        exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));
    return new ExceptionInformation(BAD_REQUEST, BAD_REQUEST.value(), message);
  }
}
