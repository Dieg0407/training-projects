package com.dapg.handmade.marketplace.engine.productlisting.application;

import org.springframework.stereotype.Service;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Artisan;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanRepository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

@Service
public class UpdateProductInformationUseCase {
  private final ProductRepository productRepository;
  private final ArtisanRepository artisanRepository;

  public UpdateProductInformationUseCase(ProductRepository productRepository,
      ArtisanRepository artisanRepository) {
    this.productRepository = productRepository;
    this.artisanRepository = artisanRepository;
  }

  public void changeTitle(ArtisanId artisanId, ProductId productId, String title) {
    var holder = getArtisanAndProduct(artisanId, productId);
    var artisan = holder.artisan;
    var product = holder.product;

    productRepository.save(artisan.changeProductTitle(product, title));
  }

  public void changeDescription(ArtisanId artisanId, ProductId productId, String description) {
    var holder = getArtisanAndProduct(artisanId, productId);
    var artisan = holder.artisan;
    var product = holder.product;

    productRepository.save(artisan.changeProductDescription(product, description));
  }

  public void changePrice(ArtisanId artisanId, ProductId productId, double price) {
    var holder = getArtisanAndProduct(artisanId, productId);
    var artisan = holder.artisan;
    var product = holder.product;

    productRepository.save(artisan.changeProductPrice(product, price));
  }

  public void changeStock(ArtisanId artisanId, ProductId productId, double stock) {
    var holder = getArtisanAndProduct(artisanId, productId);
    var artisan = holder.artisan;
    var product = holder.product;

    productRepository.save(artisan.changeProductStock(product, stock));
  }

  private ArtisanAndProductHolder getArtisanAndProduct(ArtisanId artisanId, ProductId productId) {
    var artisan = artisanRepository.findById(artisanId)
        .orElseThrow(() -> new IllegalArgumentException("Artisan not found"));
    var product = productRepository.findById(productId)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    return new ArtisanAndProductHolder(artisan, product);
  }

  static class ArtisanAndProductHolder {
    public Artisan artisan;
    public Product product;

    public ArtisanAndProductHolder(Artisan artisan, Product product) {
      this.artisan = artisan;
      this.product = product;
    }
  }
}
