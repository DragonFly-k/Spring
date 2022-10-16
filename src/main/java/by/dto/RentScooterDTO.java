package by.dto;

import by.model.Account;
import by.model.AccountScooter;
import by.model.Scooter;
import by.services.AccountService;
import by.services.ScooterService;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class RentScooterDTO {
    private Long accountId;

    private Long scooterId;
}