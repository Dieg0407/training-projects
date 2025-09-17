package com.dieg0407.demo.grpc.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class UserProfile {

  private UserProfileId id;
  private String firstName;
  private String lastName;

  public UserProfile(final UserProfileId id, final String firstName, final String lastName) {
    Assert.notNull(id, "UserProfile id must not be null");
    Assert.hasText(firstName, "UserProfile firstName must not be null or blank");
    Assert.hasText(lastName, "UserProfile lastName must not be null or blank");

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UserProfile(final String firstName, final String lastName) {
    this(new UserProfileId(), firstName, lastName);
  }
}
