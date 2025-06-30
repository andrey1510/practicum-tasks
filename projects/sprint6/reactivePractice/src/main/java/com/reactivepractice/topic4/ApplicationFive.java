package com.reactivepractice.topic4;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class ApplicationFive {

    @Configuration
    static class R2dbcConfiguration extends AbstractR2dbcConfiguration {

        @Override
        @Bean
        public ConnectionFactory connectionFactory() {
            return ConnectionFactories.get("r2dbc:h2:mem:///practicum?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        }

        @Bean
        public ValidatePersonIdBeforeSaveCallback validatePersonIdBeforeConvertCallback() {
            return new ValidatePersonIdBeforeSaveCallback();
        }

    }

    static class ValidatePersonIdBeforeSaveCallback implements BeforeSaveCallback<Person> {
        @Override
        public Publisher<Person> onBeforeSave(Person entity, OutboundRow row, SqlIdentifier table) {
            if (Objects.isNull(entity.getId()) || entity.getId() <= 0L) {
                return Mono.error(new IllegalArgumentException("Недопустимое значение идентификатора"));
            }
            return Mono.just(entity);
        }
    }

    @Table("persons")
    static class Person {
        @Id
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
        R2dbcEntityTemplate template = ctx.getBean(R2dbcEntityTemplate.class);

        template.getDatabaseClient()
            .sql("create table if not exists persons(id bigint primary key, name text)")
            .then()
            .block();

        Person okPerson = new Person();
        okPerson.setId(1L); // Корректное значение идентификатора
        okPerson.setName("Correct");

        template.insert(okPerson)
            .subscribe(it -> System.out.println("Запись успешно вставлена"));

        Person illegalIdPerson = new Person();
        illegalIdPerson.setId(-1L); // Некорректное значение идентификатора
        illegalIdPerson.setName("Incorrect");

        template.insert(illegalIdPerson)
            .doOnError(e -> System.out.println("Ошибка при вставке Person: " + e.getMessage()))
            .subscribe();
    }

}