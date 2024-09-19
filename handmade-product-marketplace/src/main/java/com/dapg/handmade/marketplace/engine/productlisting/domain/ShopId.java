package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record ShopId(UUID id) {
  public ShopId {
    Assert.notNull(id, "The id cannot be null");
  }

  public ShopId() {
    this(UUID.randomUUID());
  }
}
