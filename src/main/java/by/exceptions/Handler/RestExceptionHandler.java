package by.exceptions.Handler;

import by.exceptions.ScooterValidationException;
import by.exceptions.AccountAuthException;
import by.exceptions.AccountValidationException;
import by.exceptions.ScooterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccountValidationException.class)
    public final ResponseEntity<Object> handleUserValidationException(AccountValidationException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        List<Map<String, String>> errors = new LinkedList<>();
        for (FieldError el: ex.getBindingResult().getFieldErrors()) {
            Map<String,String> error = new LinkedHashMap<>();
            error.put("field", el.getField());
            error.put("message", el.getDefaultMessage());
            errors.add(error);
        }

        body.put("errors", errors);
        log.info(errors.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountAuthException.class)
    public final ResponseEntity<Object> handleAccountAuthException(AccountAuthException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("errors", "Wrong credentials!");
        log.info("Wrong credentials!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ScooterException.class)
    public final ResponseEntity<Object> handleScooterException(ScooterException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("error", ex.getMessage());
        log.info(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ScooterValidationException.class)
    public final ResponseEntity<Object> handleProductValidationException(ScooterValidationException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        List<Map<String, String>> errors = new LinkedList<>();

        for (FieldError el: ex.getBindingResult().getFieldErrors()) {
            Map<String,String> error = new LinkedHashMap<>();
            error.put("field", el.getField());
            error.put("message", el.getDefaultMessage());
            errors.add(error);
        }

        body.put("errors", errors);
        log.info(errors.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}