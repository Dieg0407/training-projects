package com.dapg.handmade.marketplace.engine.productlisting.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

@Repository
class MysqlProductRepository implements ProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public MysqlProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(Product product) {
    var sql =
        "INSERT INTO productlisting.products (id, artisan_id, title, description, price, stock) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, product.id().id().toString(), product.artisanId().id().toString(),
        product.title(), product.description(), product.price(), product.stock());
  }
}
