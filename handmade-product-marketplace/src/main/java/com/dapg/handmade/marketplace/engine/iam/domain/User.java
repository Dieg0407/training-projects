package com.dapg.handmade.marketplace.engine.iam.domain;

import org.springframework.util.Assert;

public class User {
  private UserId id;
  private String username;
  private Password password;

  public User(UserId id, String username, Password password) {
    Assert.notNull(id, "User id is required");
    Assert.hasText(username, "Username is required");
    Assert.notNull(password, "Password is required");

    this.id = id;
    this.username = username;
    this.password = password;
  }

  public User(String username, String password) {
    this(new UserId(), username, Password.fromPlainText(password));
  }

  public UserId id() {
    return id;
  }

  public String username() {
    return username;
  }

  public Password password() {
    return password;
  }
}
