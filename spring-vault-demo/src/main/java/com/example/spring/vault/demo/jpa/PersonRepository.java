package com.example.spring.vault.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findOneBySocialSecurityNumber(String socialSecurityNumber);

    Person findOneByIdentifier(UUID identifier);
}
