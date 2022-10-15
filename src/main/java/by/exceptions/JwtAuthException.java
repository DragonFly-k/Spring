package by.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtAuthException extends AuthenticationException {

    public JwtAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthException(String msg) {
        super(msg);
    }

}