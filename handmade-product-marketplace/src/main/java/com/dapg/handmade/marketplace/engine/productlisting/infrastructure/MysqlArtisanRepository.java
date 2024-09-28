package com.dapg.handmade.marketplace.engine.productlisting.infrastructure;

import java.sql.SQLException;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Artisan;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanRepository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.CraftType;

@Repository
class MysqlArtisanRepository implements ArtisanRepository, RowMapper<Artisan> {
  private JdbcTemplate jdbcTemplate;

  public MysqlArtisanRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public boolean verifyThatArtisanExists(ArtisanId id) {
    var sql = "SELECT COUNT(*) FROM productlisting.artisan WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Integer.class, id.value().toString()) > 0;
  }

  @Override
  public Optional<Artisan> findById(ArtisanId id) {
    var sql = "SELECT id, craft_type FROM productlisting.artisan WHERE id = ?";
    return jdbcTemplate.queryForStream(sql, this, id.value().toString()).findFirst();
  }

  @Override
  public void save(Artisan artisan) {
    var sql = "INSERT INTO productlisting.artisan (id, craft_type) VALUES (?, ?)";
    jdbcTemplate.update(sql, artisan.id().value().toString(), artisan.craftType().name());
  }

  @Override
  public Artisan mapRow(@SuppressWarnings("null") java.sql.ResultSet rs, int rowNum)
      throws SQLException {
    CraftType craftType;
    try {
      craftType = CraftType.valueOf(rs.getString("craft_type"));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid craft type is stored in the database");
    }

    var id = java.util.UUID.fromString(rs.getString("id"));
    return new Artisan(new ArtisanId(id), craftType);
  }
}
