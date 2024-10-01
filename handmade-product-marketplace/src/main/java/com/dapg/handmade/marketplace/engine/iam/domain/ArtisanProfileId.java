package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.UUID;

public record ArtisanProfileId(UUID value) {
  public ArtisanProfileId {
    if (value == null) {
      throw new IllegalArgumentException("ArtisanProfileId cannot be null");
    }
  }

  public ArtisanProfileId() {
    this(UUID.randomUUID());
  }
}
