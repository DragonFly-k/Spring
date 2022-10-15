package by.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AccountEscooter")
public class AccountEscooter extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escooter_id")
    private Escooter escooter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "rentaltime")
    private Time rentaltime;

}