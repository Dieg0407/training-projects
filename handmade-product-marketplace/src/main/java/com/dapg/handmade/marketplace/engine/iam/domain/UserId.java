package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record UserId(UUID value) {
  public UserId {
    Assert.notNull(value, "UserId must not be null");
  }

  public UserId() {
    this(UUID.randomUUID());
  }
}
