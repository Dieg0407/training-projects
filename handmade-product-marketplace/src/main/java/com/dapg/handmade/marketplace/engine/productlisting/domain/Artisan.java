package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.springframework.util.Assert;

public class Artisan {
  private ArtisanId id;
  private CraftType craftType;

  public Artisan(CraftType craftType) {
    this(new ArtisanId(), craftType);
  }

  public Artisan(ArtisanId id, CraftType craftType) {
    Assert.notNull(id, "Id is required");
    Assert.notNull(craftType, "Craft type is required");
    this.id = id;
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

  public Product changeProductTitle(Product product, String title) {
    if (!product.artisanId().equals(id)) {
      throw new IllegalArgumentException("Product does not belong to this artisan!");
    }
    product.changeTitle(title);
    return product;
  }

  public Product changeProductDescription(Product product, String description) {
    if (!product.artisanId().equals(id)) {
      throw new IllegalArgumentException("Product does not belong to this artisan!");
    }
    product.changeDescription(description);
    return product;
  }

  public Product changeProductPrice(Product product, double price) {
    if (!product.artisanId().equals(id)) {
      throw new IllegalArgumentException("Product does not belong to this artisan!");
    }
    product.changePrice(price);
    return product;
  }

  public Product changeProductStock(Product product, double stock) {
    if (!product.artisanId().equals(id)) {
      throw new IllegalArgumentException("Product does not belong to this artisan!");
    }
    product.changeStock(stock);
    return product;
  }
}
