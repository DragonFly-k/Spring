package by.validation;

import by.dto.ScooterDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ScooterValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ScooterDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}