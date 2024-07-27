package com.dieg0407.ddd.registration.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record AccountId(UUID uuid) {

    public AccountId {
        Assert.notNull(uuid, "UUID cannot be null!");
    }

    public AccountId() {
        this(UUID.randomUUID());
    }
}
