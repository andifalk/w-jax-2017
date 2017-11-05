package com.example.reactivespringvault.book;

import com.example.reactivespringvault.book.dataaccess.Book;
import com.example.reactivespringvault.book.dataaccess.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.ReactiveVaultTemplate;
import org.springframework.vault.support.VaultResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    private final BookRepository bookRepository;
    private final ReactiveVaultTemplate vaultTemplate;

    public DataInitializer(BookRepository bookRepository, ReactiveVaultTemplate vaultTemplate) {
        this.bookRepository = bookRepository;
        this.vaultTemplate = vaultTemplate;
    }

    @Override
    public void run(String... args)  {

        readVaultData();
        storeBooks();
    }

    private void storeBooks() {

        Stream.of(new Book("9780134494166",
                        "Clean Architecture: A Craftsman's Guide to Software Structure and Design",
                            "Martin’s Clean Architecture doesn’t merely present options. " +
                                    "Drawing on over a half-century of experience in software environments of " +
                                    "every imaginable type, Martin tells you what choices to make and why they are " +
                                    "critical to your success."),
                new Book("9780321125217",
                        "Domain-Driven Design: Tackling Complexity in the Heart of Software",
                        "Eric Evans has written a fantastic book on how you can make the design of your " +
                                "software match your mental model of the problem domain you are addressing"))
                .forEach(
                    b -> bookRepository.findBookByIsbn(b.getIsbn()).hasElement().subscribe(
                        hasElement -> {
                            if (!hasElement) {
                                bookRepository.save(b).subscribe();
                            }
                        }
                    )
                );
    }

    private void readVaultData() {
        Mono<VaultResponse> secretValue = vaultTemplate.read("/secret/reactive-spring-vault");
        secretValue.subscribe(c -> {
            Map<String, Object> data = c.getData();
            LOGGER.info("Read data from vault {}", data);
        }, err -> LOGGER.error(err.getMessage()));
    }
}
