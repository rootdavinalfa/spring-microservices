package xyz.dvnlabs.corelibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends Exception{

	public InvalidInputException(String message){
    	super(message);
    }
}
