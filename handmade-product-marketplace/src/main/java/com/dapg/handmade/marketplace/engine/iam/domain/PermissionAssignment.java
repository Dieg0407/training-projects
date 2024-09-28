package com.dapg.handmade.marketplace.engine.iam.domain;

import org.springframework.util.Assert;

public class PermissionAssignment {
  private PermissionAssignmentId id;
  private RoleId roleId;
  private Permission permission;

  public PermissionAssignment(PermissionAssignmentId id, RoleId roleId, Permission permission) {
    Assert.notNull(id, "PermissionAssignmentId cannot be null");
    Assert.notNull(roleId, "RoleId cannot be null");
    Assert.notNull(permission, "Permission cannot be null");

    this.id = id;
    this.roleId = roleId;
    this.permission = permission;
  }

  public PermissionAssignment(RoleId roleId, Permission permission) {
    this(new PermissionAssignmentId(), roleId, permission);
  }

  public PermissionAssignmentId id() {
    return id;
  }

  public RoleId roleId() {
    return roleId;
  }

  public Permission permission() {
    return permission;
  }
}
