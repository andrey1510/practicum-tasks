package com.springdatapractice.services;

import com.springdatapractice.entities.Account;
import com.springdatapractice.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
public class TransactionTemplateService {
    private final TransactionTemplate transactionTemplate;
    private final AccountRepository accountRepository;

    public TransactionTemplateService(
        AccountRepository accountRepository,
        PlatformTransactionManager transactionManager
    ) {
        this.accountRepository = accountRepository;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    public void transfer(Account source, Account target, BigDecimal amount) {
        transactionTemplate.executeWithoutResult(status -> {
            source.setBalance(source.getBalance().subtract(amount));
            target.setBalance(target.getBalance().add(amount));

            accountRepository.save(target);
            accountRepository.save(source);
        });
    }
}