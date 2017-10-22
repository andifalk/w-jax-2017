package com.example.spring.vault.demo.jpa;

import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import java.util.UUID;

@Component
public class RandomUUIDGenerator implements IdGenerator {

    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }
}
