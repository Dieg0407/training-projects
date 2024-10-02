package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static com.dapg.handmade.marketplace.engine.iam.infrastructure.MysqlUserRepositoryIntegrationTest.createUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.dapg.handmade.marketplace.engine.MysqlTest;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfile;
import com.dapg.handmade.marketplace.engine.iam.domain.UserId;

@MysqlTest
public class MysqlCustomerProfileRepositoryIntegrationTest {
  private final JdbcTemplate jdbcTemplate;
  private final MysqlUserRepository mysqlUserRepository;
  private final MysqlCustomerProfileRepository mysqlCustomerProfileRepository;

  @Autowired
  public MysqlCustomerProfileRepositoryIntegrationTest(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.mysqlUserRepository = new MysqlUserRepository(jdbcTemplate);
    this.mysqlCustomerProfileRepository = new MysqlCustomerProfileRepository(jdbcTemplate);
  }

  @Test
  void shouldSaveCustomerProfile() {
    var user = createUser("forCustomerProfileSaveTest");
    mysqlUserRepository.save(user);

    var customerProfile = createCustomerProfile(user.id());
    mysqlCustomerProfileRepository.save(customerProfile);

    var savedCustomerProfile = jdbcTemplate.queryForMap(
        "SELECT id, full_name, address, user_id FROM iam.customer_profile WHERE id = ?",
        customerProfile.id().value().toString());

    assertThat(savedCustomerProfile.get("id")).isEqualTo(customerProfile.id().value().toString());
    assertThat(savedCustomerProfile.get("user_id"))
        .isEqualTo(customerProfile.userId().value().toString());
    assertThat(savedCustomerProfile.get("full_name")).isEqualTo(customerProfile.fullName());
    assertThat(savedCustomerProfile.get("address")).isEqualTo(customerProfile.address());
  }

  public static CustomerProfile createCustomerProfile(UserId userId) {
    return new CustomerProfile("Jhon Doe", "address1", userId);
  }

}
