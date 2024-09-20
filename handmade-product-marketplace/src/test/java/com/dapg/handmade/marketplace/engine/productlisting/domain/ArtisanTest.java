package com.dapg.handmade.marketplace.engine.productlisting.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArtisanTest {

  @Test
  void shouldCreateAnArtisan() {
    Artisan artisan = new Artisan(CraftType.WOODWORK);
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

}
