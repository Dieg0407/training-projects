package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.springframework.util.Assert;

public class Product {
  private ProductId id;
  private String title;
  private String description;
  private double price;
  private double stock;
  private ArtisanId artisanId;

  public Product(ProductId id, String title, String description, double price, double stock,
      ArtisanId artisanId) {
    Assert.notNull(id, "ID is required");
    Assert.notNull(title, "Title is required");
    Assert.hasText(title, "Title cannot be empty");
    Assert.notNull(description, "Description is required");
    Assert.hasText(description, "Description cannot be empty");
    Assert.isTrue(price > 0, "Price must be greater than zero");
    Assert.isTrue(stock >= 0, "Stock must be greater than or equal to zero");
    Assert.notNull(artisanId, "Artisan ID is required");

    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.artisanId = artisanId;
  }

  public Product(String title, String description, double price, double stock,
      ArtisanId artisanId) {
    this(new ProductId(), title, description, price, stock, artisanId);
  }

  public void changeTitle(String title) {
    Assert.notNull(title, "Title is required");
    Assert.hasText(title, "Title cannot be empty");
    this.title = title;
  }

  public void changeDescription(String description) {
    Assert.notNull(description, "Description is required");
    Assert.hasText(description, "Description cannot be empty");
    this.description = description;
  }

  public void changePrice(double price) {
    Assert.isTrue(price > 0, "Price must be greater than zero");
    this.price = price;
  }

  public void changeStock(double stock) {
    Assert.isTrue(stock >= 0, "Stock must be greater than or equal to zero");
    this.stock = stock;
  }

  public ProductId id() {
    return id;
  }

  public String title() {
    return title;
  }

  public String description() {
    return description;
  }

  public double price() {
    return price;
  }

  public double stock() {
    return stock;
  }

  public ArtisanId artisanId() {
    return artisanId;
  }
}
