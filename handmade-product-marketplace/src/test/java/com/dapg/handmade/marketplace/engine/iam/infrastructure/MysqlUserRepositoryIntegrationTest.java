package com.dapg.handmade.marketplace.engine.iam.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.dapg.handmade.marketplace.engine.MysqlTest;
import com.dapg.handmade.marketplace.engine.iam.domain.Password;
import com.dapg.handmade.marketplace.engine.iam.domain.User;
import com.dapg.handmade.marketplace.engine.iam.domain.UserId;

@MysqlTest
public class MysqlUserRepositoryIntegrationTest {
  private final JdbcTemplate jdbcTemplate;
  private final MysqlUserRepository mysqlUserRepository;

  @Autowired
  public MysqlUserRepositoryIntegrationTest(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.mysqlUserRepository = new MysqlUserRepository(jdbcTemplate);
  }

  @Test
  void shouldSaveUser() {
    var user = createUser("forUserSaveTest");
    mysqlUserRepository.save(user);

    var savedUser = jdbcTemplate.queryForMap("SELECT * FROM iam.user WHERE id = ?",
        user.id().value().toString());

    assertThat(savedUser.get("id")).isEqualTo(user.id().value().toString());
    assertThat(savedUser.get("username")).isEqualTo(user.username());
    assertThat(savedUser.get("password")).isEqualTo(user.password().value());
  }

  @Test
  void shouldFindUserById() {
    var user = createUser("forUserFindByIdTest");
    mysqlUserRepository.save(user);

    var foundUser = mysqlUserRepository.findById(user.id());
    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().id()).isEqualTo(user.id());
    assertThat(foundUser.get().username()).isEqualTo(user.username());
    assertThat(foundUser.get().password()).isEqualTo(user.password());

    var notFoundUser = mysqlUserRepository.findById(new UserId());
    assertThat(notFoundUser).isEmpty();
  }

  @Test
  void shouldFindUserByUsername() {
    var user = createUser("forUserFindByUsernameTest");
    mysqlUserRepository.save(user);

    var foundUser = mysqlUserRepository.findByUsername(user.username());
    assertThat(foundUser).isPresent();
    assertThat(foundUser.get().id()).isEqualTo(user.id());
    assertThat(foundUser.get().username()).isEqualTo(user.username());
    assertThat(foundUser.get().password()).isEqualTo(user.password());

    var notFoundUser = mysqlUserRepository.findByUsername("notFoundUsername");
    assertThat(notFoundUser).isEmpty();
  }

  public static User createUser(String username) {
    return new User(new UserId(), username, Password.fromPlainText("1234"));
  }
}
