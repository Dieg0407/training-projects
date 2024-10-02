package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.dapg.handmade.marketplace.engine.iam.domain.Password;
import com.dapg.handmade.marketplace.engine.iam.domain.User;
import com.dapg.handmade.marketplace.engine.iam.domain.UserId;
import com.dapg.handmade.marketplace.engine.iam.domain.UserRepository;

@Repository
public class MysqlUserRepository implements UserRepository, RowMapper<User> {
  private final JdbcTemplate jdbcTemplate;

  public MysqlUserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<User> findById(UserId id) {
    var sql = "SELECT id, username, password FROM iam.user WHERE id = ?";
    return jdbcTemplate.queryForStream(sql, this, id.value().toString()).findFirst();
  }

  @Override
  public Optional<User> findByUsername(String username) {
    var sql = "SELECT id, username, password FROM iam.user WHERE username = ?";
    return jdbcTemplate.queryForStream(sql, this, username).findFirst();
  }

  @Override
  public void save(User user) {
    var sql = "INSERT INTO iam.user (id, username, password) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, user.id().value().toString(), user.username(),
        user.password().value());

  }

  @Override
  public User mapRow(@SuppressWarnings("null") java.sql.ResultSet rs, int rowNum)
      throws java.sql.SQLException {
    var id = java.util.UUID.fromString(rs.getString("id"));
    var username = rs.getString("username");
    var password = rs.getString("password");

    return new User(new UserId(id), username, Password.fromEncoded(password));
  }
}
