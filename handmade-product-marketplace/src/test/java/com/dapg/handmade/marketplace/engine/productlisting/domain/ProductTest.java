package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

  @Test
  void shouldCreateAProduct() {
    ArtisanId artisanId = new ArtisanId();
    Product product = new Product("My Product", "My Product Description", 10.0, 100.0, artisanId);
    assertThat(product.id()).isNotNull();
    assertThat(product.title()).isEqualTo("My Product");
    assertThat(product.description()).isEqualTo("My Product Description");
    assertThat(product.price()).isEqualTo(10.0);
    assertThat(product.stock()).isEqualTo(100.0);
    assertThat(product.artisanId()).isEqualTo(artisanId);
  }

  @Test
  void shouldChangeTitle() {
    Product product =
        new Product("My Product", "My Product Description", 10.0, 100.0, new ArtisanId());
    product.changeTitle("New Title");
    assertThat(product.title()).isEqualTo("New Title");
  }

  @Test
  void shouldChangeDescription() {
    Product product =
        new Product("My Product", "My Product Description", 10.0, 100.0, new ArtisanId());
    product.changeDescription("New Description");
    assertThat(product.description()).isEqualTo("New Description");
  }

  @Test
  void shouldChangePrice() {
    Product product =
        new Product("My Product", "My Product Description", 10.0, 100.0, new ArtisanId());
    product.changePrice(20.0);
    assertThat(product.price()).isEqualTo(20.0);
  }

  @Test
  void shouldChangeStock() {
    Product product =
        new Product("My Product", "My Product Description", 10.0, 100.0, new ArtisanId());
    product.changeStock(50.0);
    assertThat(product.stock()).isEqualTo(50.0);
  }

  @Test
  void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(
        () -> new Product(null, "My Product Description", 10.0, 100.0, new ArtisanId()))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Title is required");
  }

  @Test
  void shouldThrowExceptionWhenTitleIsEmpty() {
    assertThatThrownBy(
        () -> new Product("", "My Product Description", 10.0, 100.0, new ArtisanId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Title cannot be empty");
  }

  @Test
  void shouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(() -> new Product("My Product", null, 10.0, 100.0, new ArtisanId()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Description is required");
  }

  @Test
  void shouldThrowExceptionWhenDescriptionIsEmpty() {
    assertThatThrownBy(() -> new Product("My Product", "", 10.0, 100.0, new ArtisanId()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Description cannot be empty");
  }

  @Test
  void shouldThrowExceptionWhenPriceIsZeroOrNegative() {
    assertThatThrownBy(
        () -> new Product("My Product", "My Product Description", 0.0, 100.0, new ArtisanId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Price must be greater than zero");

    assertThatThrownBy(
        () -> new Product("My Product", "My Product Description", -1.0, 100.0, new ArtisanId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Price must be greater than zero");
  }

  @Test
  void shouldThrowExceptionWhenStockIsNegative() {
    assertThatThrownBy(
        () -> new Product("My Product", "My Product Description", 10.0, -1.0, new ArtisanId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Stock must be greater than or equal to zero");
  }
}
