package br.com.videomentor.api.exceptions;

/**
 * InvalidTokenException.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class InvalidTokenException extends RuntimeException {

	public InvalidTokenException(String message) {
		super(message);
	}

	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}