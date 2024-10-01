package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.UUID;

public record CustomerProfileId(UUID value) {

  public CustomerProfileId {
    if (value == null) {
      throw new IllegalArgumentException("Customer profile id is required");
    }
  }

  public CustomerProfileId() {
    this(UUID.randomUUID());
  }

}
