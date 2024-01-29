package br.com.videomentor.api.exceptions;

/**
 * InvalidPasswordException.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class InvalidPasswordException extends RuntimeException {
	public InvalidPasswordException(String message) {
		super(message);
	}
}