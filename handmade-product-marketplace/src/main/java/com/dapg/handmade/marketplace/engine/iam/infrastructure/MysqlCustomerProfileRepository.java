package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfile;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfileId;
import com.dapg.handmade.marketplace.engine.iam.domain.CustomerProfileRepository;
import com.dapg.handmade.marketplace.engine.iam.domain.UserId;

@Repository
public class MysqlCustomerProfileRepository
    implements CustomerProfileRepository, RowMapper<CustomerProfile> {

  private final JdbcTemplate jdbcTemplate;

  public MysqlCustomerProfileRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(CustomerProfile customerProfile) {
    var sql =
        "INSERT INTO iam.customer_profile (id, full_name, address, user_id) VALUES (?, ?, ?, ?)";

    jdbcTemplate.update(sql, customerProfile.id().value().toString(), customerProfile.fullName(),
        customerProfile.address(), customerProfile.userId().value().toString());
  }

  @Override
  public CustomerProfile mapRow(@SuppressWarnings("null") java.sql.ResultSet rs, int rowNum)
      throws java.sql.SQLException {
    var id = java.util.UUID.fromString(rs.getString("id"));
    var fullName = rs.getString("full_name");
    var address = rs.getString("address");
    var userId = java.util.UUID.fromString(rs.getString("user_id"));

    return new CustomerProfile(new CustomerProfileId(id), fullName, address, new UserId(userId));
  }

}
