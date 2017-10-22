package com.example.spring.vault.demo.api;

import com.example.spring.vault.demo.jpa.Person;
import com.example.spring.vault.demo.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonRestApi {

    private final PersonService personService;

    public PersonRestApi(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> findAllPersonsDecrypted() {
        return personService.findAll();
    }

    @GetMapping(path = "/{personId}")
    public Person findPersonDecrypted(@PathVariable UUID personId) {
        return personService.findByIdentifier(personId);
    }

    @GetMapping(path = "/encrypted")
    public List<Person> findAllPersonsEncrypted() {
        return personService.findAllEncrypted();
    }

}
