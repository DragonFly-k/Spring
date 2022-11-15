package by.security.jwt;

import by.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtAccount implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static JwtAccount fromAccountToJwtAccount(Account account) {
        JwtAccount j = new JwtAccount();
        j.login = account.getLogin();
        j.password = account.getPassword();
        j.authorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getName()));
        return j;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}