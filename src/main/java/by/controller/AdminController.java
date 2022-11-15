package by.controller;

import by.dto.ScooterDTO;
import by.exceptions.AccountAuthException;
import by.exceptions.ScooterValidationException;
import by.model.Account;
import by.model.AccountScooter;
import by.model.Scooter;
import by.services.AccountScooterService;
import by.services.AccountService;
import by.services.ScooterService;
import by.validation.ScooterValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    @Autowired
    private ScooterService scooterService;

    @Autowired
    ScooterValidator scooterValidator;

    @PostMapping(value = "addScooter")
    public ResponseEntity<Scooter> addScooter(@Valid @RequestBody ScooterDTO scooterDTO, BindingResult errors)
    {
        scooterValidator.validate(scooterDTO, errors);
        if(errors.hasErrors()) {
            throw new ScooterValidationException(errors);
        }
        Scooter scooter = scooterDTO.toScooter();
        scooterService.create(scooter);
        log.info("Post request : /api/v1/admin/addProduct");
        return new ResponseEntity<>(scooter, HttpStatus.CREATED);
    }
}