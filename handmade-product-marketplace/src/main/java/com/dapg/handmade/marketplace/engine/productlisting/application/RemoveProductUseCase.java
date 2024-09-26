package com.dapg.handmade.marketplace.engine.productlisting.application;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

@Service
public class RemoveProductUseCase {
  private final ProductRepository productRepository;

  public RemoveProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void removeProduct(ProductId productId) {
    if (productRepository.removeProduct(productId)) {
      return;
    }
    throw new IllegalArgumentException("Product does not exist");
  }
}
