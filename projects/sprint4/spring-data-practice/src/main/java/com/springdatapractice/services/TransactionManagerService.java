package com.springdatapractice.services;

import com.springdatapractice.entities.Account;
import com.springdatapractice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import java.math.BigDecimal;

@Service
@Slf4j
public class TransactionManagerService {

    private final PlatformTransactionManager transactionManager;
    private final AccountRepository accountRepository;

    public TransactionManagerService(PlatformTransactionManager transactionManager,
                                     AccountRepository accountRepository) {
        this.transactionManager = transactionManager;
        this.accountRepository = accountRepository;
    }

    public void transfer(Account source, Account target, BigDecimal amount) {

        var transaction = transactionManager.getTransaction(TransactionDefinition.withDefaults());

        try {
            target.setBalance(target.getBalance().add(amount));
            source.setBalance(source.getBalance().subtract(amount));

            accountRepository.save(target);
            accountRepository.save(source);

            transactionManager.commit(transaction);
        } catch (Exception e) {
            log.error(e.getMessage());
            transactionManager.rollback(transaction);
        }
    }
}
