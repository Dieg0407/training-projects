package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.Optional;

public interface ProductRepository {
  void save(Product product);

  Optional<Product> findById(ProductId productId);
}
