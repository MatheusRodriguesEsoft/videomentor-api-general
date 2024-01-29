package br.com.videomentor.api.exceptions;

/**
 * HandleRuntimeException.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class HandleRuntimeException extends RuntimeException {

  public HandleRuntimeException(String message) {
    super(message);
  }
}
