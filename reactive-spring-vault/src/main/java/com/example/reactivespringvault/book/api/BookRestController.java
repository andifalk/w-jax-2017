package com.example.reactivespringvault.book.api;

import com.example.reactivespringvault.book.dataaccess.Book;
import com.example.reactivespringvault.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private Supplier<? extends String> messageSupplier = () -> "hello";

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Flux<Book> books() {
        return bookService.findAll();
    }

}
