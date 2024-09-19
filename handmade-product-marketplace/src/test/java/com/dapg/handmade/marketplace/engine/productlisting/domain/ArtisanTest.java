package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArtisanTest {

  @Test
  void shouldCreateAnArtisan() {
    Artisan artisan = new Artisan(CraftType.WOODWORK, "shop name", "shop description");
    assertThat(artisan.id()).isNotNull();
    assertThat(artisan.craftType()).isEqualTo(CraftType.WOODWORK);
    assertThat(artisan.shop().name()).isEqualTo("shop name");
    assertThat(artisan.shop().description()).isEqualTo("shop description");
  }

  @Test
  void shouldFailToCreateAnArtisanWhenCraftTypeIsNull() {
    try {
      new Artisan(null, "shop name", "shop description");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Craft type is required");
    }
  }

  @Test
  void shouldFailToCreateAnArtisanWhenShopNameIsNull() {
    try {
      new Artisan(CraftType.WOODWORK, null, "shop description");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Name is required");
    }
  }

  @Test
  void shouldFailToCreateAnArtisanWhenShopNameIsEmpty() {
    try {
      new Artisan(CraftType.WOODWORK, "", "shop description");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Name cannot be empty");
    }
  }

  @Test
  void shouldChangeCraftType() {
    Artisan artisan = new Artisan(CraftType.WOODWORK, "shop name", "shop description");
    artisan.changeCraftType(CraftType.POTTERY);
    assertThat(artisan.craftType()).isEqualTo(CraftType.POTTERY);
  }
}
