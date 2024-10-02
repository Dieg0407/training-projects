package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfile;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfileId;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfileRepository;
import com.dapg.handmade.marketplace.engine.iam.domain.CraftType;
import com.dapg.handmade.marketplace.engine.iam.domain.PhoneNumber;
import com.dapg.handmade.marketplace.engine.iam.domain.UserId;
import java.util.UUID;

@Repository
public class MysqlArtisanProfileRepository
    implements ArtisanProfileRepository, RowMapper<ArtisanProfile> {

  private final JdbcTemplate jdbcTemplate;

  public MysqlArtisanProfileRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(ArtisanProfile artisanProfile) {
    var sql =
        "INSERT INTO iam.artisan_profile (id, shop_name, craft_type, phone_number, country_code, user_id) VALUES (?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(sql, artisanProfile.id().value().toString(), artisanProfile.shopName(),
        artisanProfile.craftType().name(), artisanProfile.phoneNumber().number(),
        artisanProfile.phoneNumber().countryCode(), artisanProfile.userId().value().toString());
  }

  @Override
  public ArtisanProfile mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum)
      throws SQLException {
    var id = new ArtisanProfileId(UUID.fromString(rs.getString("id")));
    var shopName = rs.getString("shop_name");
    var craftType = CraftType.valueOf(rs.getString("craft_type"));
    var phoneNumber = rs.getString("phone_number");
    var countryCode = rs.getString("country_code");
    var userId = new UserId(UUID.fromString(rs.getString("user_id")));

    return new ArtisanProfile(id, shopName, craftType, new PhoneNumber(countryCode, phoneNumber),
        userId);
  }

}
