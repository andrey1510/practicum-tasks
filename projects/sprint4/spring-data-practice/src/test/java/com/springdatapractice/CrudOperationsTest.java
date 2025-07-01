package com.springdatapractice;

import com.springdatapractice.entities.Account;
import com.springdatapractice.repository.AccountRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CrudOperationsTest extends SpringDataJdbcApplicationTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testCreate() {
        var anatoly = accountRepository.save(new Account("Анатолий"));

        assertThat(anatoly)
            .isNotNull()
            .withFailMessage("Созданной записи должен был быть присвоен ID")
            .extracting(anatoly.getId().toString())
            .isNotNull();
    }

    @Test
    public void testDelete() {
        var mariana = accountRepository.save(new Account("Мариана"));
        accountRepository.delete(mariana);

        assertThat(accountRepository.existsById(mariana.getId()))
            .withFailMessage("Удалённая запись не должна быть найдена")
            .isFalse();
    }
}
