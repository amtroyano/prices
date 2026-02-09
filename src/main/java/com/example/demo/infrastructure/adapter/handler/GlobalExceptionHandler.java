package com.example.demo.infrastructure.adapter.handler;

import com.example.demo.domain.exceptions.PriceNotFoundException;
import com.example.demo.infrastructure.adapter.inbound.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(PriceNotFoundException.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handlePriceNotFoundException(
      PriceNotFoundException ex, HttpServletRequest request) {

    ErrorResponse error =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleRuntimeException(
      RuntimeException ex, HttpServletRequest request) {

    ErrorResponse error =
        ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getMessage())
            .path(request.getRequestURI())
            .build();

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
