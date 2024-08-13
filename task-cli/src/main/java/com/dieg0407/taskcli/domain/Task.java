package com.dieg0407.taskcli.domain;

import java.time.Instant;
import java.util.Optional;

public class Task {
  private TaskId id;
  private String description;
  private TaskStatus status;
  private Instant createdAt;
  private Instant updatedAt;

  /**
   * @param description the task description that will identify it
   * @throws IllegalArgumentException if provided description is null
   */
  public Task(String description) throws IllegalArgumentException {
    this.description = description;
    this.createdAt = Instant.now();
    this.updatedAt = createdAt;
    this.status = TaskStatus.TODO;

    validateFields();
  }

  public Task(TaskId id, String description, TaskStatus status, Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;

    validateFields();
  }

  private void validateFields() {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Description can't be null or empty");
    }
    if (status == null) {
      throw new IllegalArgumentException("Status cannot be null");
    }
    if(createdAt == null) {
      throw new IllegalArgumentException("CreatedAt field can't by null");
    }
    if(updatedAt == null) {
      throw new IllegalArgumentException("UpdatedAt field can't be null");
    }
  }

  public void updateId(TaskId taskId) {
    this.id = taskId;
  }

  public void updateDescription(String description) {
    this.description = description;
    this.updatedAt = Instant.now();
  }

  public void markAsDone() {
    this.status = TaskStatus.DONE;
    this.updatedAt = Instant.now();
  }

  public void markInProgress() {
    this.status = TaskStatus.IN_PROGRESS;
    this.updatedAt = Instant.now();
  }

  // region boilerplate
  // ----------------------------------------------
  public Optional<TaskId> getId() {
    return Optional.ofNullable(id);
  }

  public String getDescription() {
    return description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  // ----------------------------------------------
  // endregion
}
