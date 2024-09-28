package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.UUID;

public record PermissionAssignmentId(UUID value) {
  public PermissionAssignmentId {
    if (value == null) {
      throw new IllegalArgumentException("PermissionAssignmentId cannot be null");
    }
  }

  public PermissionAssignmentId() {
    this(UUID.randomUUID());
  }

}
