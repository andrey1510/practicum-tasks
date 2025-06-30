package com.reactivepractice.topic4;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.LongStream;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Slf4j
public class Application {

    static class Person {
        private Long id;
        private String username;
        private Boolean active;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
    }

    static Person testPerson(Long id) {
        Person p = new Person();
        p.setId(id);
        p.setUsername("Test #" + id);
        p.setActive(id % 2 == 0);
        return p;
    }

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///practicum?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

        template.getDatabaseClient()
            .sql("create table person(id bigserial not null, username varchar(128) not null, active boolean)")
            .then()
            .block();

        Flux.fromStream(LongStream.rangeClosed(1L, 10L).boxed())
            .map(Application::testPerson)
            .flatMap(template::insert)
            .collectList()
            .block();

        List<Long> result = template.select(Person.class)
            .matching(query(where("id").greaterThan(8).and(where("active").isTrue())))
            .all()
            .map(Person::getId)
            .collectList()
            .block();

        log.info("{}", result);
    }

}
