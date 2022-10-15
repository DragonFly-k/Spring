package by.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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
    private String rentTime;

}