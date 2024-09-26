package com.dapg.handmade.marketplace.engine.productlisting.application;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;
import java.util.List;

@Service
public class SearchProductUseCase {
  private final ProductRepository productRepository;

  public SearchProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> searchByTitle(String title) {
    Assert.hasText(title, "Provided title must not be empty");
    return productRepository.searchByTitle(title);
  }
}
