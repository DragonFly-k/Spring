package by.services;

import by.model.AccountScooter;
import by.model.Scooter;
import by.model.Account;
import by.repository.AccountScooterRepository;
import by.repository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;

@Service
public class AccountScooterService {
    @Autowired
    AccountScooterRepository accountScooterRepository;

    public List<AccountScooter> getAll() {
        return accountScooterRepository.findAll();
    }

    public AccountScooter getByAccount(Account account) {
        return accountScooterRepository.getAccountScooterByAccount(account);
    }

    public void save(AccountScooter accountScooter) {
        accountScooterRepository.save(accountScooter);
    }

    @Transactional
    public void deleteByScooter(Scooter scooter) {
        accountScooterRepository.deleteByScooterId(scooter.getId());
    }
}