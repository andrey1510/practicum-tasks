package com.reactivepractice.topic4.book;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
}
