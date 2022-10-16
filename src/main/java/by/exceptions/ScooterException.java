package by.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ScooterException extends RuntimeException {

    public ScooterException(String msg, Throwable t) {
        super(msg, t);
    }

    public ScooterException(String msg) {
        super(msg);
    }
}