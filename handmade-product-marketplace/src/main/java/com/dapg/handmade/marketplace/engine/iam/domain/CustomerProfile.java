package com.dapg.handmade.marketplace.engine.iam.domain;

import org.springframework.util.Assert;

public class CustomerProfile {
  private CustomerProfileId id;
  private String fullName;
  private String address;
  private UserId userId;

  public CustomerProfile(CustomerProfileId id, String fullName, String address, UserId userId) {
    Assert.notNull(id, "Customer profile id is required");
    Assert.hasText(fullName, "Full name is required");
    Assert.hasText(address, "Address is required");
    Assert.notNull(userId, "User id is required");

    this.id = id;
    this.fullName = fullName;
    this.address = address;
    this.userId = userId;
  }

  public CustomerProfile(String fullName, String address, UserId userId) {
    this(new CustomerProfileId(), fullName, address, userId);
  }

  public CustomerProfileId id() {
    return id;
  }

  public String fullName() {
    return fullName;
  }

  public String address() {
    return address;
  }

  public UserId userId() {
    return userId;
  }
}
