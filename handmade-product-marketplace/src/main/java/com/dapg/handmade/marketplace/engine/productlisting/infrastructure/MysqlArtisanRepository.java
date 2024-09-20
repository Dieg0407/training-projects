package com.dapg.handmade.marketplace.engine.productlisting.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanRepository;

@Repository
class MysqlArtisanRepository implements ArtisanRepository {
  private JdbcTemplate jdbcTemplate;

  public MysqlArtisanRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public boolean verifyThatArtisanExists(ArtisanId id) {
    var sql = "SELECT COUNT(*) FROM productlisting.artisans WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Integer.class, id.id().toString()) > 0;
  }
}
