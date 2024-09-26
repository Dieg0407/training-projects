package com.dapg.handmade.marketplace.engine.productlisting.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

public class SearchProductUseCaseTest {
  private ProductRepository productRepository;
  private SearchProductUseCase useCase;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    useCase = new SearchProductUseCase(productRepository);
  }

  @Test
  void shouldSearchProductByTitle() {
    // When
    var title = "title";
    var product1 = new Product("title 1", "some desc", 12, 0, new ArtisanId());
    var product2 = new Product("title 2", "another desc", 10, 0, new ArtisanId());
    when(productRepository.searchByTitle(title)).thenReturn(Arrays.asList(product1, product2));

    // Given
    var result = useCase.searchByTitle(title);

    // Then
    verify(productRepository).searchByTitle(title);
    assertThat(result).containsExactlyInAnyOrder(product1, product2);
  }
}
