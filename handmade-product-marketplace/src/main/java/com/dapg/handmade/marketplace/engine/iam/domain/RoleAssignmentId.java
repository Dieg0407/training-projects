package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.UUID;

public record RoleAssignmentId(UUID value) {
  public RoleAssignmentId {
    if (value == null) {
      throw new IllegalArgumentException("RoleAssignmentId cannot be null");
    }
  }

  public RoleAssignmentId() {
    this(UUID.randomUUID());
  }

}
