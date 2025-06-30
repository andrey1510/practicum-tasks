package com.reactivepractice.topic4.book;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<Void> saveAndDeleteBook(String name) {
        return bookRepository.save(new Book(name))
            .doOnNext(book -> System.out.println("Book id " + book.getId()))
            .flatMap(book -> bookRepository.deleteById(book.getId()))
            .doOnSuccess(v -> System.out.println("Deleted"))
            .then();
    }
}
