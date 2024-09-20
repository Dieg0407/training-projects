package com.dapg.handmade.marketplace.engine.productlisting.application;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanRepository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

@Service
public class AddProductUseCase {
  private final ProductRepository productRepository;
  private final ArtisanRepository artisanRepository;

  public AddProductUseCase(ProductRepository productRepository,
      ArtisanRepository artisanRepository) {
    this.productRepository = productRepository;
    this.artisanRepository = artisanRepository;
  }

  /**
   * @param artisanId
   * @param title
   * @param description
   * @param price
   * @param stock
   * @return The created product id
   * @throws IllegalArgumentException when a parameter is not valid
   */
  public ProductId execute(ArtisanId artisanId, String title, String description, double price,
      double stock) throws IllegalArgumentException {
    if (!artisanRepository.verifyThatArtisanExists(artisanId)) {
      throw new IllegalArgumentException("Artisan does not exist");
    }

    var product = new Product(title, description, price, stock, artisanId);
    productRepository.save(product);

    return product.id();
  }
}
