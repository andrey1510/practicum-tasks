package com.springdatapractice.repository;

import com.springdatapractice.entities.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public List<Account> findAll() {
        return jdbcTemplate.query(
            "SELECT id, name, balance FROM account",
            new BeanPropertyRowMapper<>(Account.class)
        );
    }

    public Account findByName(String name) {
        return jdbcTemplate.queryForObject(
            "SELECT id, name, balance FROM account WHERE name = :name LIMIT 1",
            new MapSqlParameterSource("name", name),
            new BeanPropertyRowMapper<>(Account.class)
        );
    }

    public void create(String name) {
        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
            "INSERT INTO account (name) VALUES (:name)",
            new MapSqlParameterSource("name", name),
            keyHolder,
            new String[]{"id"}
        );
    }

    public void update(Account account) {
        var params = new MapSqlParameterSource()
            .addValue("name", account.getName())
            .addValue("balance", account.getBalance())
            .addValue("id", account.getId());

        jdbcTemplate.update(
            "UPDATE account SET name = :name, balance = :balance WHERE id = :id",
            params
        );
    }
}