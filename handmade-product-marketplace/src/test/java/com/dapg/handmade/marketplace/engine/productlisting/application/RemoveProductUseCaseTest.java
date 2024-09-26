package com.dapg.handmade.marketplace.engine.productlisting.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

public class RemoveProductUseCaseTest {
  private ProductRepository productRepository;
  private RemoveProductUseCase useCase;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    useCase = new RemoveProductUseCase(productRepository);
  }

  @Test
  void shouldRemoveProduct() {
    // Given
    var productId = new ProductId();
    when(productRepository.removeProduct(productId)).thenReturn(true);

    // When
    useCase.removeProduct(productId);

    // Then
    verify(productRepository).removeProduct(productId);
  }

  @Test
  void shouldThrowExceptionIfProductDoesNotExist() {
    // Given
    var productId = new ProductId();
    when(productRepository.removeProduct(productId)).thenReturn(false);

    // When
    assertThatThrownBy(() -> useCase.removeProduct(productId))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("Product does not exist");
  }
}
