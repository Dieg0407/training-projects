package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest {
  @Test
  void shouldCreateAShop() {
    Shop shop = new Shop("My Shop", "My Shop Description");
    assertThat(shop.id()).isNotNull();
    assertThat(shop.name()).isEqualTo("My Shop");
    assertThat(shop.description()).isEqualTo("My Shop Description");
  }

  @Test
  void shouldFailToCreateAShopWhenNameIsNull() {
    try {
      new Shop(null, "My Shop Description");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Name is required");
    }
  }

  @Test
  void shouldFailToCreateAShopWhenNameIsEmpty() {
    try {
      new Shop("", "My Shop Description");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Name cannot be empty");
    }
  }

  @Test
  void shouldFailToCreateAShopWhenDescriptionIsNull() {
    try {
      new Shop("My Shop", null);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Description is required");
    }
  }

  @Test
  void shouldFailToCreateAShopWhenDescriptionIsEmpty() {
    try {
      new Shop("My Shop", "");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Description cannot be empty");
    }
  }
}
