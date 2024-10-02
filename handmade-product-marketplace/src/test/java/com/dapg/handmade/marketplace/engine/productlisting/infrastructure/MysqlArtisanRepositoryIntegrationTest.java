package com.dapg.handmade.marketplace.engine.productlisting.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.dapg.handmade.marketplace.engine.MysqlTest;
import com.dapg.handmade.marketplace.engine.productlisting.domain.Artisan;
import com.dapg.handmade.marketplace.engine.productlisting.domain.ArtisanId;
import com.dapg.handmade.marketplace.engine.productlisting.domain.CraftType;

@MysqlTest
public class MysqlArtisanRepositoryIntegrationTest {
  private final JdbcTemplate jdbcTemplate;
  private final MysqlArtisanRepository mysqlArtisanRepository;

  @Autowired
  public MysqlArtisanRepositoryIntegrationTest(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.mysqlArtisanRepository = new MysqlArtisanRepository(jdbcTemplate);
  }

  @Test
  void shouldSaveArtisan() {
    // given
    var artisanId = new ArtisanId();
    var artisan = new Artisan(artisanId, CraftType.CROCHET);

    // when
    mysqlArtisanRepository.save(artisan);

    // then
    var artisanFromDb =
        jdbcTemplate.queryForMap("SELECT id, craft_type FROM productlisting.artisan WHERE id = ?",
            new Object[] {artisanId.value().toString()});

    assertThat(artisanFromDb.get("id")).isNotNull().isEqualTo(artisanId.value().toString());
    assertThat(artisanFromDb.get("craft_type")).isNotNull().isEqualTo(CraftType.CROCHET.toString());
  }

  @Test
  void shouldFetchArtisanById() {
    // given
    var artisanId = new ArtisanId();
    var artisan = new Artisan(artisanId, CraftType.CROCHET);
    mysqlArtisanRepository.save(artisan);

    // when
    var existingArtisan = mysqlArtisanRepository.findById(artisanId);
    var validateExistingArtisan = mysqlArtisanRepository.verifyThatArtisanExists(artisanId);
    var nonExistingArtisan = mysqlArtisanRepository.findById(new ArtisanId());
    var validateNonExistingArtisan =
        mysqlArtisanRepository.verifyThatArtisanExists(new ArtisanId());

    // then
    assertThat(existingArtisan).isPresent().get().isEqualTo(artisan);
    assertThat(nonExistingArtisan).isEmpty();
    assertThat(validateExistingArtisan).isTrue();
    assertThat(validateNonExistingArtisan).isFalse();
  }
}
