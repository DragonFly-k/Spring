package by.security;

import by.security.jwt.JwtAccount;
import by.model.Account;
import by.services.AccountService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public JwtUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public JwtAccount loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException("User with login: " + login + " not found");
        }
        return JwtAccount.fromAccountToJwtAccount(account);
    }
}