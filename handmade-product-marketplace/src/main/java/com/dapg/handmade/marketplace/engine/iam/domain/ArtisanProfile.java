package com.dapg.handmade.marketplace.engine.iam.domain;

import org.springframework.util.Assert;

public class ArtisanProfile {
  private ArtisanProfileId id;
  private String shopName;
  private CraftType craftType;
  private PhoneNumber phoneNumber;
  private UserId userId;

  public ArtisanProfile(ArtisanProfileId id, String shopName, CraftType craftType,
      PhoneNumber phoneNumber, UserId userId) {
    Assert.notNull(id, "id must not be null");
    Assert.hasText(shopName, "shopName must not be empty");
    Assert.notNull(craftType, "craftType must not be null");
    Assert.notNull(phoneNumber, "phoneNumber must not be null");
    Assert.notNull(userId, "userId must not be null");

    this.id = id;
    this.shopName = shopName;
    this.craftType = craftType;
    this.phoneNumber = phoneNumber;
  }

  public ArtisanProfile(String shopName, CraftType craftType, String countryCode,
      String phoneNumber, UserId userId) {
    this(new ArtisanProfileId(), shopName, craftType, new PhoneNumber(countryCode, phoneNumber),
        userId);
  }

  public ArtisanProfileId id() {
    return id;
  }

  public String shopName() {
    return shopName;
  }

  public CraftType craftType() {
    return craftType;
  }

  public PhoneNumber phoneNumber() {
    return phoneNumber;
  }

  public UserId userId() {
    return userId;
  }
}
