package by.controller;

import by.config.security.jwt.JwtTokenProvider;
import by.model.Account;
import by.services.AccountService;
import by.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/api/v1/account")
public class AccountController {
    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

//    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Account> signUp(@Valid @RequestBody AuthAccountDTO accountDetails, BindingResult errors)
//    {
//        accountValidator.validate(accountDetails, errors);
//
//        if(errors.hasErrors()) {
//            throw new AccountValidationException(errors);
//        }
//
//        Account account = accountDetails.ToAccount();
//        accountService.signup(account);
////        log.info("Get request : /api/v1/auth/registerStudent");
//        return new ResponseEntity<>(account, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity signIn(@RequestBody AuthAccountDTO accountDetails) {
//        String login = accountDetails.getLogin();
//        String password = accountDetails.getPassword();
//        Account account = accountService.findByLoginAndPassword(login, password);
//        if(account == null) throw new AccountAuthException("Wrong Credentials!");
//        String token = jwtTokenProvider.createToken(login);
//        Map<Object, Object> response = new HashMap<>();
//        response.put("token", token);
//        response.put("accountId", account.getId());
//        response.put("role", account.getRole().getName());
//        return ResponseEntity.ok(response);

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestParam Map<String, String> mapParam) {
        Long accountId = Long.parseLong(mapParam.get("accountId"));
        Account account = accountService.findById(accountId);
        Boolean isAdmin = "ROLE_ADMIN".equals(account.getRole().getName());
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }
}