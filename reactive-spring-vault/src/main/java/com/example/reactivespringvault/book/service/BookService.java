package com.example.reactivespringvault.book.service;

import com.example.reactivespringvault.book.dataaccess.Book;
import com.example.reactivespringvault.book.dataaccess.BookRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Book> save(Book entity) {
        return bookRepository.save(entity);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Mono<Book> findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }
}
