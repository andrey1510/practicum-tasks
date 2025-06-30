package com.redispractice;

import com.redispractice.dto.CarPrice;
import com.redispractice.repositories.CarPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringDataRedisApplication implements ApplicationRunner {

    @Autowired
    private CarPriceRepository carPriceRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Кешируем информацию о машине Василия...");
        carPriceRepository.save(new CarPrice("МашинаВасилия", BigDecimal.valueOf(1_000_00)));

        System.out.println("Проверяем записанные в Redis машины — " + carPriceRepository.findAll());

        System.out.println("Ждём, пока пройдёт TTL (12 секунд)");
        TimeUnit.SECONDS.sleep(12);

        System.out.println("По прошествии TTL в Redis остались машины — " + carPriceRepository.findAll());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisApplication.class, args);
    }
}
