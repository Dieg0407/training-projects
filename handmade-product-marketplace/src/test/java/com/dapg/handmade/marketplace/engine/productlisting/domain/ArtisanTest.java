package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArtisanTest {

  @Test
  void shouldCreateAnArtisan() {
    var artisan = new Artisan(CraftType.WOODWORK);
    assertThat(artisan.id()).isNotNull();
    assertThat(artisan.craftType()).isEqualTo(CraftType.WOODWORK);
  }

  @Test
  void shouldFailToCreateAnArtisanWhenCraftTypeIsNull() {
    try {
      new Artisan(null);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Craft type is required");
    }
  }

  @Test
  void shouldFailToChangeCraftTypeWhenCraftTypeIsNull() {
    var artisan = new Artisan(CraftType.WOODWORK);
    try {
      artisan.changeCraftType(null);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Craft type is required");
    }
  }

  @Test
  void shouldChangeCraftType() {
    var artisan = new Artisan(CraftType.WOODWORK);
    artisan.changeCraftType(CraftType.POTTERY);
    assertThat(artisan.craftType()).isEqualTo(CraftType.POTTERY);
  }

  @Test
  void shouldFailToChangeProductTitleWhenProductDoesNotBelongToArtisan() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var anotherArtisan = new Artisan(CraftType.POTTERY);
    var product =
        new Product(new ProductId(), "title", "description", 10.0, 10.0, anotherArtisan.id());
    try {
      artisan.changeProductTitle(product, "new title");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Product does not belong to this artisan!");
    }
  }

  @Test
  void shouldChangeProductTitle() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var product = new Product(new ProductId(), "title", "description", 10.0, 10.0, artisan.id());
    artisan.changeProductTitle(product, "new title");
    assertThat(product.title()).isEqualTo("new title");
  }

  @Test
  void shouldFailToChangeProductDescriptionWhenProductDoesNotBelongToArtisan() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var anotherArtisan = new Artisan(CraftType.POTTERY);
    var product =
        new Product(new ProductId(), "title", "description", 10.0, 10.0, anotherArtisan.id());
    try {
      artisan.changeProductDescription(product, "new description");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Product does not belong to this artisan!");
    }
  }

  @Test
  void shouldChangeProductDescription() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var product = new Product(new ProductId(), "title", "description", 10.0, 10.0, artisan.id());
    artisan.changeProductDescription(product, "new description");
    assertThat(product.description()).isEqualTo("new description");
  }

  @Test
  void shouldFailToChangeProductPriceWhenProductDoesNotBelongToArtisan() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var anotherArtisan = new Artisan(CraftType.POTTERY);
    var product =
        new Product(new ProductId(), "title", "description", 10.0, 10.0, anotherArtisan.id());
    try {
      artisan.changeProductPrice(product, 20.0);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Product does not belong to this artisan!");
    }
  }

  @Test
  void shouldChangeProductPrice() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var product = new Product(new ProductId(), "title", "description", 10.0, 10.0, artisan.id());
    artisan.changeProductPrice(product, 20.0);
    assertThat(product.price()).isEqualTo(20.0);
  }

  @Test
  void shouldFailToChangeProductStockWhenProductDoesNotBelongToArtisan() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var anotherArtisan = new Artisan(CraftType.POTTERY);
    var product =
        new Product(new ProductId(), "title", "description", 10.0, 10.0, anotherArtisan.id());
    try {
      artisan.changeProductStock(product, 20.0);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Product does not belong to this artisan!");
    }
  }

  @Test
  void shouldChangeProductStock() {
    var artisan = new Artisan(CraftType.WOODWORK);
    var product = new Product(new ProductId(), "title", "description", 10.0, 10.0, artisan.id());
    artisan.changeProductStock(product, 20.0);
    assertThat(product.stock()).isEqualTo(20.0);
  }
}
