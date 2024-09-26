package com.dapg.handmade.marketplace.engine.productlisting.domain;

import java.util.Optional;
import java.util.List;

public interface ProductRepository {
  void save(Product product);

  Optional<Product> findById(ProductId productId);

  boolean removeProduct(ProductId productId);

  List<Product> searchByTitle(String title);
}
