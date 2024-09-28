package com.dapg.handmade.marketplace.engine.iam.domain;

import org.springframework.util.Assert;

public class Role {
  private RoleId id;
  private String name;
  private String description;

  public Role(RoleId id, String name, String description) {
    Assert.notNull(id, "RoleId cannot be null");
    Assert.hasText(name, "Name cannot be null or empty");
    Assert.hasText(description, "Description cannot be null or empty");
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Role(String name, String description) {
    this(new RoleId(), name, description);
  }

  public RoleId id() {
    return id;
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

}
