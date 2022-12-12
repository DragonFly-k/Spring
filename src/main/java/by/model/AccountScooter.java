package by.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "account_scooter")
public class AccountScooter extends BaseEntity{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "rent_time")
    private Date rentTime;

    public AccountScooter(Scooter scooter, Account account) {
        this.scooter = scooter;
        this.account = account;
        this.rentTime = new Date();
    }
}