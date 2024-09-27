package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.Optional;

public interface ArtisanRepository {
  boolean verifyThatArtisanExists(ArtisanId id);

  Optional<Artisan> findById(ArtisanId id);

  void save(Artisan artisan);
}
