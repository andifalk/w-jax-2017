package com.example.spring.vault.demo;

import com.example.spring.vault.demo.common.KeyNames;
import com.example.spring.vault.demo.jpa.Person;
import com.example.spring.vault.demo.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;

import java.util.stream.Stream;

@Service
public class DataInitializer implements CommandLineRunner {

    private final PersonService personService;

    private final VaultOperations vaultOperations;

    public DataInitializer(PersonService personService, VaultOperations vaultOperations) {
        this.personService = personService;
        this.vaultOperations = vaultOperations;
    }

    @Override
    public void run(String... args) throws Exception {
        if (vaultOperations.opsForTransit().getKey(KeyNames.KEY_SSN) == null) {
            vaultOperations.opsForTransit().createKey(KeyNames.KEY_SSN);
        }
        if (vaultOperations.opsForTransit().getKey(KeyNames.KEY_CREDIT_CARD) == null) {
            vaultOperations.opsForTransit().createKey(KeyNames.KEY_CREDIT_CARD);
        }
        Stream.of(
            new Person("Donald", "Duck",
                    "111111", "1111-1111-1111-11"),
                new Person("Mickey", "Mouse",
                        "222222", "2222-2222-2222-22")
        ).forEach(personService::save);
    }
}
