package by.controller;
import by.aop.LogAnnotation;

import by.dto.RentScooterDTO;
import by.exceptions.ScooterException;
import by.model.Account;
import by.model.AccountScooter;
import by.model.Scooter;
import by.services.AccountScooterService;
import by.services.AccountService;
import by.services.ScooterService;
import by.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/v1/scooter")
public class ScooterController {
    @Autowired
    private ScooterService scooterService;

    @Autowired
    private AccountScooterService accountScooterService;

    @Autowired
    private AccountService accountService;

    @Autowired
    MailService mailService;

    @LogAnnotation
    @GetMapping(value = "list")
    public ResponseEntity<List<Scooter>> getScooters() {
        List<Scooter> scooters = scooterService.getAll();
        log.info("Get query : /api/v1/scooter/list");
        return new ResponseEntity<>(scooters, HttpStatus.OK);
    }

    @GetMapping(value = "availabilityList")
    public ResponseEntity<List<AccountScooter>> getAvailabilityScooters() {
        List<AccountScooter> scooters = accountScooterService.getAll();
        log.info("Get query : /api/v1/scooter/availabilityList");
        return new ResponseEntity<>(scooters, HttpStatus.OK);
    }

    @GetMapping(value = "get")
    public ResponseEntity<Scooter> getScooter(@RequestParam Map<String, String> mapParam) {
        Long scooterId = Long.parseLong(mapParam.get("id"));
        Scooter scooter = scooterService.getById(scooterId);
        if(scooter == null) {
            throw new ScooterException("Scooter with id " + scooterId + " not found!");
        }
        log.info("Get query : /api/v1/scooter/get");
        return new ResponseEntity<>(scooter, HttpStatus.OK);
    }

    @PostMapping(value = "rent")
    public ResponseEntity rentScooter(RequestEntity<RentScooterDTO> rentScooterDTO) {
        Account account = accountService.findById(rentScooterDTO.getBody().getAccountId());
        Scooter scooter = scooterService.getById(rentScooterDTO.getBody().getScooterId());
        AccountScooter accountScooter = new AccountScooter(scooter, account);
        accountScooterService.save(accountScooter);
        log.info("Post query : /api/v1/scooter/rent");
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "rent")
    public ResponseEntity deleteRentScooter(RequestEntity<RentScooterDTO> rentScooterDTO) throws MessagingException {
        Scooter scooter = scooterService.getById(rentScooterDTO.getBody().getScooterId());
        Account account = accountService.findById(rentScooterDTO.getBody().getAccountId());
        AccountScooter accountScooter = accountScooterService.getByAccount(account);
        mailService.sendMailAboutRent(accountScooter);
        accountScooterService.deleteByScooter(scooter);
        log.info("Delete query : /api/v1/scooter/rent");
        return new ResponseEntity(HttpStatus.OK);
    }
}