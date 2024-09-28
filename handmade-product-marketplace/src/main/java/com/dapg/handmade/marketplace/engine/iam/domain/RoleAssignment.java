package com.dapg.handmade.marketplace.engine.iam.domain;

public class RoleAssignment {
  private final RoleAssignmentId id;
  private final UserId userId;
  private final Role role;

  public RoleAssignment(RoleAssignmentId id, UserId userId, Role role) {
    this.id = id;
    this.userId = userId;
    this.role = role;
  }

  public RoleAssignmentId id() {
    return id;
  }

  public UserId userId() {
    return userId;
  }

  public Role role() {
    return role;
  }

}
