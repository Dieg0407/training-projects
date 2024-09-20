package com.dapg.handmade.marketplace.engine.productlisting.domain;

public interface ArtisanRepository {
  boolean verifyThatArtisanExists(ArtisanId id);
}
