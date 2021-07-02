package xyz.dvnlabs.corelibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceExistException extends Exception {

    public ResourceExistException(String messageStr) {
        super(messageStr);
    }
}
