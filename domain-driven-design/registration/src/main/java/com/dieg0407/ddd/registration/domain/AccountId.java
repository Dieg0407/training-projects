package com.dieg0407.ddd.registration.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record AccountId(UUID id) {

  public AccountId {
    Assert.notNull(id, "UUID cannot be null!");
  }

  public AccountId() {
    this(UUID.randomUUID());
  }
}
