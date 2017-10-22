package com.example.spring.vault.demo.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Person extends AbstractPersistable<Long> {

    private UUID identifier;

    private String firstName;

    private String lastName;

    private String socialSecurityNumber;

    private String creditCardNumber;

    public Person(String firstName, String lastName, String socialSecurityNumber, String creditCardNumber) {
        this(null, firstName, lastName, socialSecurityNumber, creditCardNumber);
    }

    public Person(UUID identifier, String firstName, String lastName, String socialSecurityNumber, String creditCardNumber) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.creditCardNumber = creditCardNumber;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("id")
    public UUID getIdentifier() {
        return identifier;
    }

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return super.isNew();
    }
}
