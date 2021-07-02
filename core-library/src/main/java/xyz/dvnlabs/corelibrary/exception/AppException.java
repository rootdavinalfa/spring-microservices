package xyz.dvnlabs.corelibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new App exception.
	 *
	 * @param message the message
	 */
	public AppException(String message){
    	super(message);
    }

	/**
	 * Instantiates a new App exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new App exception.
	 *
	 * @param cause the cause
	 */
	public AppException(Throwable cause) {
		super(cause);
	}
}
