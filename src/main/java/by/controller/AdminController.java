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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping(value = "/addScooter/{model}/{price}")
    public ResponseEntity<Scooter> addScooter(@PathVariable String model, @PathVariable int price) {
        Scooter scooter = new Scooter(model, price);
        if (scooterService.getByModel(model) != null) {
            log.info("Scooter with this model already exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            scooterService.create(scooter);
            log.info("Scooter added");
            return new ResponseEntity<>(scooter, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteScooter/{model}")
    public ResponseEntity<Scooter> deleteScooter(@PathVariable String model) {
        Scooter scooter = scooterService.getByModel(model);
        if (scooter == null) {
            log.info("Scooter with model " + model + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            scooterService.delete(scooter);
            log.info("Delete request : /api/v1/admin/deleteScooter/" + model);
            return new ResponseEntity<>(scooter, HttpStatus.OK);
        }
    }}