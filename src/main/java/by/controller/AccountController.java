package by.controller;

import by.exceptions.AccountAuthException;
import by.model.Account;
import by.model.AccountScooter;
import by.services.AccountScooterService;
import by.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountScooterService accountScooterService;

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        if(account != null) {
            Boolean isAdmin = "ADMIN".equals(account.getRole().getName());
            return new ResponseEntity<>(isAdmin, HttpStatus.OK);
        }
        else throw new AccountAuthException("Account not found!");
    }

    @GetMapping(value = "/rent")
    public ResponseEntity<AccountScooter> getMyRent(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        if(account != null) {
            AccountScooter accountScooter = accountScooterService.getByAccount(account);
            if(accountScooter != null) return new ResponseEntity<>(accountScooter, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else throw new AccountAuthException("Account not found!");
    }
}