package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.UUID;

public record RoleId(UUID value) {
  public RoleId {
    if (value == null) {
      throw new IllegalArgumentException("RoleId cannot be null");
    }
  }

  public RoleId() {
    this(UUID.randomUUID());
  }
}
