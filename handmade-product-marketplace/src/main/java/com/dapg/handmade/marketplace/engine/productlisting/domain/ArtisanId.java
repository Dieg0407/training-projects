package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record ArtisanId(UUID value) {
  public ArtisanId {
    Assert.notNull(value, "The id cannot be null");
  }

  public ArtisanId() {
    this(UUID.randomUUID());
  }
}
