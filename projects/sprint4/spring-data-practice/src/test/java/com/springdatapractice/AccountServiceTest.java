package com.springdatapractice;

import com.springdatapractice.entities.Account;
import com.springdatapractice.repository.AccountDao;
import com.springdatapractice.services.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceTest extends SpringDataJdbcApplicationTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testSuccessfullSqlQueries() {

        accountDao.create("Пётр");
        accountDao.create("Василий");
        var petrAccount = accountDao.findByName("Пётр");
        var vasilyAccount = accountDao.findByName("Василий");
        var initialBalance = petrAccount.getBalance();

        Assertions.assertThrows(
            UncategorizedSQLException.class,
            () -> accountService.transfer(vasilyAccount, petrAccount, BigDecimal.valueOf(100_000L))
        );

        assertThat(accountDao.findAll())
            .isNotEmpty()
            .withFailMessage("При возникновении ошибки во время транзакции " +
                "балансы обоих пользователей должны вернуться к изначальным значениям")
            .map(Account::getBalance)
            .allMatch(it -> it.compareTo(initialBalance) == 0);
    }
}
