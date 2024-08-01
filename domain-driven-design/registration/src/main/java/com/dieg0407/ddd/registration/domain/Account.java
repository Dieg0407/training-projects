package com.dieg0407.ddd.registration.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;

import java.util.Objects;

@Entity
@Table(name = "accounts", schema = "registration",
    uniqueConstraints = @UniqueConstraint(name = "uk_username", columnNames = {"username"}))
public class Account {
  @EmbeddedId
  private AccountId id;
  private String username;
  @Embedded
  @AttributeOverride(name = "validEmail", column = @Column(name = "email"))
  private Email email;
  private String password;

  Account() {}

  public Account(String username, Email email, String password) {
    Assert.notNull(username, "The account 'username' can't be null");
    Assert.notNull(email, "The account 'email' can't be null");
    Assert.notNull(password, "The account 'password' can't be null");

    this.id = new AccountId();
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public AccountId getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Account account = (Account) o;
    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
