package com.dieg0407.demo.grpc.user.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record UserProfileId(UUID value) {

  public UserProfileId {
    Assert.notNull(value, "UserProfileId value must not be null");
  }

  public UserProfileId() {
    this(UUID.randomUUID());
  }
}
