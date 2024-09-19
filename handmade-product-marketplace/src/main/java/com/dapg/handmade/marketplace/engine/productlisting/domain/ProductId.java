package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record ProductId(UUID id) {
  public ProductId {
    Assert.notNull(id, "The id cannot be null");
  }

  public ProductId() {
    this(UUID.randomUUID());
  }
}
