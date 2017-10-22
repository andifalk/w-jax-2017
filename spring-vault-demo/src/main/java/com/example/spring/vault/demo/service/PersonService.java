package com.example.spring.vault.demo.service;

import com.example.spring.vault.demo.common.KeyNames;
import com.example.spring.vault.demo.jpa.Person;
import com.example.spring.vault.demo.jpa.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;
import org.springframework.vault.core.VaultOperations;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

    private final IdGenerator idGenerator;

    private final PersonRepository personRepository;

    private final VaultOperations vaultOperations;

    public PersonService(IdGenerator idGenerator, PersonRepository personRepository, VaultOperations vaultOperations) {
        this.idGenerator = idGenerator;
        this.personRepository = personRepository;
        this.vaultOperations = vaultOperations;
    }

    public List<Person> findAll() {
        return personRepository
                .findAll()
                .stream()
                .map(this::decryptSensibleData)
                .collect(Collectors.toList());
    }

    public List<Person> findAllEncrypted() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        if (person.getIdentifier() == null) {
            person.setIdentifier(idGenerator.generateId());
        }
        encryptSensibleData(person);
        return personRepository.save(person);
    }


    public Person findByIdentifier(UUID identifier) {
        Person person = personRepository.findOneByIdentifier(identifier);
        return decryptSensibleData(person);
    }

    public Person findBySocialSecurityNumber(String socialSecurityNumber) {
        return personRepository.findOneBySocialSecurityNumber(socialSecurityNumber);
    }

    private void encryptSensibleData(Person person) {
        try {
            person.setSocialSecurityNumber(
                    vaultOperations.opsForTransit().encrypt(KeyNames.KEY_SSN,
                            Base64.getEncoder().encodeToString(
                                    person.getSocialSecurityNumber().getBytes("UTF-8"))));
            person.setCreditCardNumber(
                    vaultOperations.opsForTransit().encrypt(KeyNames.KEY_CREDIT_CARD,
                            Base64.getEncoder().encodeToString(
                                    person.getCreditCardNumber().getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error when encrypting data", e);
        }
    }

    private Person decryptSensibleData(Person person) {
        try {
            person.setSocialSecurityNumber(
                    new String(
                        Base64.getDecoder().decode(
                            vaultOperations.opsForTransit().decrypt(KeyNames.KEY_SSN,
                                    person.getSocialSecurityNumber())), "UTF-8"));
            person.setCreditCardNumber(
                    new String(
                            Base64.getDecoder().decode(
                                    vaultOperations.opsForTransit().decrypt(KeyNames.KEY_CREDIT_CARD,
                                            person.getCreditCardNumber())), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error when encrypting data", e);
        }
        return person;
    }

}
