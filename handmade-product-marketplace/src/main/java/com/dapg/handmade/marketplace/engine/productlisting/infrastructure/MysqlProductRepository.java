package com.dapg.handmade.marketplace.engine.productlisting.infrastructure;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Product;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ProductRepository;

@Repository
class MysqlProductRepository implements ProductRepository, RowMapper<Product> {

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

  @Override
  public Optional<Product> findById(ProductId productId) {
    var sql =
        "SELECT id, artisan_id, title, description, price, stock FROM productlisting.products WHERE id = ?";

    return jdbcTemplate.queryForStream(sql, this, productId.id()).findFirst();
  }

  @Override
  public boolean removeProduct(ProductId productId) {
    var sql = "DELETE FROM productlisting.products WHERE id = ?";
    return jdbcTemplate.update(sql, productId.id()) > 0;
  }

  @Override
  public List<Product> searchByTitle(String title) {
    var sql =
        "SELECT id, artisan_id, title, description, price, stock FROM productlisting.products WHERE lower(title) LIKE ?";
    return jdbcTemplate.query(sql, this, title.toLowerCase());
  }

  @Override
  public Product mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
    var id = UUID.fromString(rs.getString("id"));
    var artisanId = UUID.fromString(rs.getString("artisan_id"));
    var title = rs.getString("title");
    var description = rs.getString("description");
    var price = rs.getDouble("price");
    var stock = rs.getDouble("stock");

    return new Product(new ProductId(id), title, description, price, stock,
        new ArtisanId(artisanId));
  }
}
