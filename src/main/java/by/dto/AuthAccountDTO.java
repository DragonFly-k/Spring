package by.dto;

import by.model.Account;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class AuthAccountDTO {

    @NotBlank(message = "Account username cannot be empty")
    @Length(min = 8, max = 40, message = "Account username must be between 8 and 40 characters")
    private String login;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 8, max = 16, message = "Password length must be between 8 and 16 characters")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;

    public Account ToAccount() {
        return new Account(this.getLogin(),this.getPassword(), this.getEmail());
    }

}