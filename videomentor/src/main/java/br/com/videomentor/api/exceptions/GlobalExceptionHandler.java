package br.com.videomentor.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(
    AuthenticationException ex
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      ex.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(
    NotFoundException ex
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      ex.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(HandleRuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(
    HandleRuntimeException ex
  ) {
    ErrorResponse errorResponse = new ErrorResponse(
      ex.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  public static class ErrorResponse {

    private String message;

    private Long timestamp;

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public Long getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
    }

    public ErrorResponse(String message, Long timestamp) {
      this.message = message;
      this.timestamp = timestamp;
    }
  }
}
