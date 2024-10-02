package com.dapg.handmade.marketplace.engine.productlisting.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.dapg.handmade.marketplace.engine.MysqlTest;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Artisan;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.CraftType;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;

@MysqlTest
public class MysqlProductRepositoryIntegrationTest {
  private final JdbcTemplate jdbcTemplate;
  private final MysqlProductRepository mysqlProductRepository;
  private final MysqlArtisanRepository mysqlArtisanRepository;

  @Autowired
  public MysqlProductRepositoryIntegrationTest(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.mysqlProductRepository = new MysqlProductRepository(this.jdbcTemplate);
    this.mysqlArtisanRepository = new MysqlArtisanRepository(this.jdbcTemplate);
  }

  @Test
  void shouldCreateAProduct() {
    // when
    var product = createProduct("some random title");
    var id = product.id();

    // then
    // assert that the product was created
    var result = jdbcTemplate.queryForMap("SELECT * FROM productlisting.product where id = ?",
        id.value().toString());

    assertThat(result.get("id")).isEqualTo(id.value().toString());
    assertThat(result.get("title")).isEqualTo(product.title());
    assertThat(result.get("description")).isEqualTo(product.description());
    assertThat(((BigDecimal) result.get("price")).doubleValue()).isEqualTo(product.price());
    assertThat(((BigDecimal) result.get("stock")).doubleValue()).isEqualTo(product.stock());
    assertThat(result.get("artisan_id")).isEqualTo(product.artisanId().value().toString());
  }

  @Test
  void shouldFindProductByid() {
    // given
    var product = createProduct("some random title");
    var id = product.id();

    // when
    var result = mysqlProductRepository.findById(id);
    var nonExistentResult = mysqlProductRepository.findById(new ProductId());

    // then
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(product);
    assertThat(nonExistentResult).isEmpty();
  }

  @Test
  void shouldRemoveProduct() {
    // given
    var product = createProduct("some random title");
    // when
    var result = mysqlProductRepository.removeProduct(product.id());

    // then
    assertThat(result).isTrue();
    assertThat(jdbcTemplate.queryForList("SELECT * FROM productlisting.product where id = ?",
        product.id().value().toString())).isEmpty();
  }

  @Test
  void shouldFindByTitleCaseInsensitive() {
    // given
    var product1 = createProduct("some random title");
    var product2 = createProduct("some random title v2");
    var product3 = createProduct("ax some random title");
    var product4 = createProduct("non related title");

    // when
    var result = mysqlProductRepository.searchByTitle("some random title");

    // then
    assertThat(result).containsAnyOf(product1, product2, product3);
    assertThat(result).doesNotContain(product4);
  }

  private Product createProduct(String title) {
    var artisanId = new ArtisanId();
    var artisan = new Artisan(artisanId, CraftType.CROCHET);
    mysqlArtisanRepository.save(artisan);

    var productId = new ProductId();
    var product = new Product(productId, title, "some description", 10, 15, artisanId);
    mysqlProductRepository.save(product);

    return product;
  }
}
