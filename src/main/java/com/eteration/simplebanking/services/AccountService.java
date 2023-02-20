package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).orElse(null);
    }

    public void saveAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }

}
