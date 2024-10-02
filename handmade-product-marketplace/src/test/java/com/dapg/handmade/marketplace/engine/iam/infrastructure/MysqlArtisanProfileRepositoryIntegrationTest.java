package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static com.dapg.handmade.marketplace.engine.iam.infrastructure.MysqlUserRepositoryIntegrationTest.createUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.dapg.handmade.marketplace.engine.MysqlTest;
import com.dapg.handmade.marketplace.engine.iam.domain.ArtisanProfile;
import com.dapg.handmade.marketplace.engine.iam.domain.CraftType;
import com.dapg.handmade.marketplace.engine.iam.domain.UserId;

@MysqlTest
public class MysqlArtisanProfileRepositoryIntegrationTest {
  private final JdbcTemplate jdbcTemplate;
  private final MysqlUserRepository mysqlUserRepository;
  private final MysqlArtisanProfileRepository mysqlArtisanProfileRepository;

  @Autowired
  public MysqlArtisanProfileRepositoryIntegrationTest(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.mysqlUserRepository = new MysqlUserRepository(jdbcTemplate);
    this.mysqlArtisanProfileRepository = new MysqlArtisanProfileRepository(jdbcTemplate);
  }

  @Test
  void shouldSaveArtisanProfile() {
    var user = createUser("forArtisanProfileSaveTest");
    mysqlUserRepository.save(user);

    var artisanProfile = createArtisanProfile(user.id());
    mysqlArtisanProfileRepository.save(artisanProfile);

    var savedArtisanProfile = jdbcTemplate.queryForMap(
        "SELECT id, shop_name, craft_type, phone_number, country_code, user_id FROM iam.artisan_profile WHERE user_id = ?",
        artisanProfile.userId().value().toString());

    assertThat(savedArtisanProfile.get("id")).isEqualTo(artisanProfile.id().value().toString());
    assertThat(savedArtisanProfile.get("user_id"))
        .isEqualTo(artisanProfile.userId().value().toString());
    assertThat(savedArtisanProfile.get("shop_name")).isEqualTo(artisanProfile.shopName());
    assertThat(savedArtisanProfile.get("craft_type")).isEqualTo(artisanProfile.craftType().name());
    assertThat(savedArtisanProfile.get("phone_number"))
        .isEqualTo(artisanProfile.phoneNumber().number());
    assertThat(savedArtisanProfile.get("country_code"))
        .isEqualTo(artisanProfile.phoneNumber().countryCode());
  }

  public static ArtisanProfile createArtisanProfile(UserId userId) {
    return new ArtisanProfile("shop", CraftType.ACCESSORIES, "+51", "999999999", userId);
  }
}
