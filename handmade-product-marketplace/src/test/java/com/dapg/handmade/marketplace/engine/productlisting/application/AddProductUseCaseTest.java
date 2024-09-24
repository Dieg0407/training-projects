package com.dapg.handmade.marketplace.engine.productlisting.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanRepository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

public class AddProductUseCaseTest {
  private ProductRepository productRepository;
  private ArtisanRepository artisanRepository;
  private AddProductUseCase useCase;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    artisanRepository = mock(ArtisanRepository.class);

    useCase = new AddProductUseCase(productRepository, artisanRepository);
  }

  @Test
  void shouldAddProduct() {
    // Given
    var artisanId = new ArtisanId();
    var title = "title";
    var description = "description";
    var price = 10.0;
    var stock = 10.0;

    when(artisanRepository.verifyThatArtisanExists(artisanId)).thenReturn(true);

    // When
    var productId = useCase.execute(artisanId, title, description, price, stock);

    // Then
    verify(artisanRepository).verifyThatArtisanExists(artisanId);
    verify(productRepository).save(argThat(product -> product.title().equals(title)
        && product.description().equals(description) && product.price() == price
        && product.stock() == stock && product.artisanId().equals(artisanId)));

    assertThat(productId).isNotNull();
  }

  @Test
  void shouldFailIfArtisanDoesNotExist() {
    // Given
    var artisanId = new ArtisanId();

    // When
    try {
      useCase.execute(artisanId, "title", "description", 10.0, 10.0);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Artisan does not exist");
    }
  }
}
