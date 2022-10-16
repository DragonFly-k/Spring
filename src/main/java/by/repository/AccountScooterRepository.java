package by.repository;

import by.model.AccountScooter;
import by.model.Account;
import by.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountScooterRepository extends JpaRepository<AccountScooter, Long> {
    public void deleteByScooterId(Long scooterId);
    public AccountScooter getAccountScooterByAccount(Account account);
}