package com.springdatapractice.services;

import com.springdatapractice.entities.Account;
import com.springdatapractice.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionalAnnotationService {

    private final AccountRepository accountRepository;

    public TransactionalAnnotationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    void transfer(Account source, Account target, BigDecimal amount) {
        target.setBalance(target.getBalance().add(amount));
        accountRepository.save(target);
        source.setBalance(source.getBalance().subtract(amount));
        accountRepository.save(source);
    }
}
