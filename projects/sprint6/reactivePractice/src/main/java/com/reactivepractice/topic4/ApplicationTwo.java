package com.reactivepractice.topic4;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import reactor.core.publisher.Flux;

import java.util.stream.LongStream;

public class ApplicationTwo {

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
        p.setActive(Boolean.TRUE);
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

        template.update(Person.class)
            .matching(Query.query(Criteria.where("id").greaterThanOrEquals(5)))
            .apply(Update.update("active", Boolean.FALSE))
            .subscribe(it -> System.out.println("Обновлено строк:" + it));

        template.delete(Person.class)
            .matching(Query.query(Criteria.where("active").isTrue()))
            .all()
            .subscribe(it -> System.out.println("Удалено строк:" + it));
    }

}
