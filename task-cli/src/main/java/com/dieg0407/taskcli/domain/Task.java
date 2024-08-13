package com.dieg0407.taskcli.domain;

import java.time.Instant;
import java.util.Optional;

public class Task {
  private TaskId id;
  private String description;
  private TaskStatus status;
  private Instant createdAt;
  private Instant updatedAt;

  Task() {}

  /**
   * @param description the task description that will identify it
   * @throws IllegalArgumentException if provided description is null
   */
  public Task(String description) throws IllegalArgumentException {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Field 'description' can't be null or empty");
    }
    this.description = description;
    this.createdAt = Instant.now();
    this.updatedAt = createdAt;
    this.status = TaskStatus.TODO;
  }

  public Task(TaskId id, String description, TaskStatus status, Instant createdAt,
      Instant updatedAt) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Field 'description' can't be null or empty");
    }
    this.id = id;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void updateId(TaskId taskId) {
    this.id = taskId;
  }

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

}
