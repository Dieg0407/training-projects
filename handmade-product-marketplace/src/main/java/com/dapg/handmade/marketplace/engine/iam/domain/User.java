package com.dapg.handmade.marketplace.engine.iam.domain;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.util.Assert;

public class User {
  private UserId id;
  private String username;
  private String encodedPassword;

  public User(UserId id, String username, String password) {
    Assert.notNull(id, "User id is required");
    Assert.hasText(username, "Username is required");
    Assert.hasText(password, "Password is required");

    this.id = id;
    this.username = username;
    this.encodedPassword = encodePassword(password);
  }

  public User(String username, String password) {
    this(new UserId(), username, password);
  }

  public UserId id() {
    return id;
  }

  public String username() {
    return username;
  }

  public boolean passwordMatches(String password) {
    return encodedPassword.equals(encodePassword(password));
  }

  private static String encodePassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public RoleAssignment assignRole(Role role) {
    return new RoleAssignment(new RoleAssignmentId(), id, role);
  }
}
