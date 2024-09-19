package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.springframework.util.Assert;

public class Shop {
  private ShopId id;
  private String name;
  private String description;

  public Shop(String name, String description) {
    Assert.notNull(name, "Name is required");
    Assert.hasText(name, "Name cannot be empty");
    Assert.notNull(description, "Description is required");
    Assert.hasText(description, "Description cannot be empty");

    this.id = new ShopId();
    this.name = name;
    this.description = description;
  }

  public void changeName(String name) {
    Assert.notNull(name, "Name is required");
    Assert.hasText(name, "Name cannot be empty");
    this.name = name;
  }

  public void changeDescription(String description) {
    Assert.notNull(description, "Description is required");
    Assert.hasText(description, "Description cannot be empty");
    this.description = description;
  }

  public ShopId id() {
    return id;
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }
}
