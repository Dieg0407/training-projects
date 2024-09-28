package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record ProductId(UUID value) {
  public ProductId {
    Assert.notNull(value, "The id cannot be null");
  }

  public ProductId() {
    this(UUID.randomUUID());
  }
}
