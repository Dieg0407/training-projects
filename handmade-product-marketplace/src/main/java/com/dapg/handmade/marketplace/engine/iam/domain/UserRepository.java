package com.dapg.handmade.marketplace.engine.iam.domain;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findByUsername(String username);

  Optional<User> findById(UserId id);

  void save(User user);
}
