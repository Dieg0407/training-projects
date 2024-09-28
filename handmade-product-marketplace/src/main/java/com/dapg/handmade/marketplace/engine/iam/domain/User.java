package com.dapg.handmade.marketplace.engine.iam.domain;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.util.Assert;

public class User {
  private UserId id;
  private String username;
  private String encodedPassword;
  private UserType type;

  public User(UserId id, String username, String password, UserType type) {
    Assert.notNull(id, "User id is required");
    Assert.hasText(username, "Username is required");
    Assert.hasText(password, "Password is required");
    Assert.notNull(type, "User type is required");

    this.id = id;
    this.username = username;
    this.encodedPassword = encodePassword(password);
    this.type = type;
  }

  public UserId id() {
    return id;
  }

  public String username() {
    return username;
  }

  public UserType type() {
    return type;
  }

  public boolean passwordMatches(String password) {
    return encodedPassword.equals(encodePassword(password));
  }

  private static String encodePassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

}
