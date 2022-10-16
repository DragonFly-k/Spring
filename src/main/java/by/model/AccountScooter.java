package by.model;

import by.services.AccountService;
import by.services.ScooterService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AccountScooter")
public class AccountScooter extends BaseEntity{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "rentTime")
    private Date rentTime;

    public AccountScooter(Scooter scooter, Account account) {
        this.scooter = scooter;
        this.account = account;
        this.rentTime = new Date();
    }
}