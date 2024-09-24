package com.dapg.handmade.marketplace.engine.productlisting.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Artisan;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanRepository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.CraftType;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

public class UpdateProductInformationUseCaseTest {
  private ArtisanRepository artisanRepository;
  private ProductRepository productRepository;

  private UpdateProductInformationUseCase useCase;

  @BeforeEach
  void setUp() {
    artisanRepository = mock(ArtisanRepository.class);
    productRepository = mock(ProductRepository.class);

    useCase = new UpdateProductInformationUseCase(productRepository, artisanRepository);
  }

  @Test
  void shouldChangeProductDescription() {
    // Given
    var artisan = createArtisan();
    var product = createProduct(artisan.id());

    when(artisanRepository.findById(artisan.id())).thenReturn(Optional.of(artisan));
    when(productRepository.findById(product.id())).thenReturn(Optional.of(product));

    // When
    useCase.changeDescription(artisan.id(), product.id(), "new description");

    // Then
    verify(productRepository).save(argThat(p -> p.description().equals("new description")));
  }

  @Test
  void shouldChangeProductTitle() {
    // Given
    var artisan = createArtisan();
    var product = createProduct(artisan.id());

    when(artisanRepository.findById(artisan.id())).thenReturn(Optional.of(artisan));
    when(productRepository.findById(product.id())).thenReturn(Optional.of(product));

    // When
    useCase.changeTitle(artisan.id(), product.id(), "new title");

    // Then
    verify(productRepository).save(argThat(p -> p.title().equals("new title")));
  }

  @Test
  void shouldChangeProductPrice() {
    // Given
    var artisan = createArtisan();
    var product = createProduct(artisan.id());

    when(artisanRepository.findById(artisan.id())).thenReturn(Optional.of(artisan));
    when(productRepository.findById(product.id())).thenReturn(Optional.of(product));

    // When
    useCase.changePrice(artisan.id(), product.id(), 20.0);

    // Then
    verify(productRepository).save(argThat(p -> p.price() == 20.0));
  }

  @Test
  void shouldChangeProductStock() {
    // Given
    var artisan = createArtisan();
    var product = createProduct(artisan.id());

    when(artisanRepository.findById(artisan.id())).thenReturn(Optional.of(artisan));
    when(productRepository.findById(product.id())).thenReturn(Optional.of(product));

    // When
    useCase.changeStock(artisan.id(), product.id(), 10.0);

    // Then
    verify(productRepository).save(argThat(p -> p.stock() == 10.0));
  }

  @Test
  void shouldFailIfArtisanDoesNotExist() {
    // Given
    var artisan = createArtisan();
    var product = createProduct(artisan.id());

    when(artisanRepository.findById(artisan.id())).thenReturn(Optional.empty());

    // When
    assertThatThrownBy(
        () -> useCase.changeDescription(artisan.id(), product.id(), "new description"))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("Artisan not found");
  }

  @Test
  void shouldFailIfProductDoesNotExist() {
    // Given
    var artisan = createArtisan();
    var product = createProduct(artisan.id());

    when(artisanRepository.findById(artisan.id())).thenReturn(Optional.of(artisan));
    when(productRepository.findById(product.id())).thenReturn(Optional.empty());

    // When
    assertThatThrownBy(
        () -> useCase.changeDescription(artisan.id(), product.id(), "new description"))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("Product not found");
  }

  private Artisan createArtisan() {
    return new Artisan(CraftType.CROCHET);
  }

  private Product createProduct(ArtisanId artisanId) {
    return new Product(new ProductId(), "product title", "product description", 10.0, 5.0,
        artisanId);
  }
}
