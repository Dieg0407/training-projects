package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.HashSet;
import java.util.Set;
import org.springframework.util.Assert;

public class Artisan {
  private ArtisanId id;
  private CraftType craftType;
  private Shop shop;
  private Set<ProductId> productIds;

  public Artisan(CraftType craftType, String shopName, String shopDescription) {
    Assert.notNull(craftType, "Craft type is required");
    this.id = new ArtisanId();
    this.craftType = craftType;
    this.shop = new Shop(shopName, shopDescription);
    this.productIds = new HashSet<>();
  }

  public void addProduct(Product product) {
    Assert.notNull(product, "Product is required");
    productIds.add(product.id());
  }

  public ArtisanId id() {
    return id;
  }

  public CraftType craftType() {
    return craftType;
  }

  public Shop shop() {
    return shop;
  }

  public Set<ProductId> productIds() {
    return productIds;
  }

  public void changeCraftType(CraftType craftType) {
    Assert.notNull(craftType, "Craft type is required");
    this.craftType = craftType;
  }
}
