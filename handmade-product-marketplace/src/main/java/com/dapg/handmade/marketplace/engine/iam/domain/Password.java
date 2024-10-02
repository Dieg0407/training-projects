package com.dapg.handmade.marketplace.engine.iam.domain;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
  private final String value;

  private Password(String value) {
    this.value = value;
  }

  public static Password fromEncoded(String value) {
    return new Password(value);
  }

  public static Password fromPlainText(String value) {
    return new Password(encodePassword(value));
  }

  public boolean matches(String rawPassword) {
    return BCrypt.checkpw(rawPassword, this.value);
  }

  private static String encodePassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Password password = (Password) o;

    return value.equals(password.value);
  }
}
