package com.springdatapractice;

import com.springdatapractice.configs.MySqlTestcontainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ImportTestcontainers(MySqlTestcontainer.class)
@ActiveProfiles("test")
public class SpringDataJdbcApplicationTest {

    @Test
    void contextLoads() {
    }
}
