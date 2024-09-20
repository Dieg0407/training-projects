package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.springframework.util.Assert;

public class Artisan {
  private ArtisanId id;
  private CraftType craftType;

  public Artisan(CraftType craftType) {
    Assert.notNull(craftType, "Craft type is required");
    this.id = new ArtisanId();
    this.craftType = craftType;
  }

  public ArtisanId id() {
    return id;
  }

  public CraftType craftType() {
    return craftType;
  }

  public void changeCraftType(CraftType craftType) {
    Assert.notNull(craftType, "Craft type is required");
    this.craftType = craftType;
  }
}
