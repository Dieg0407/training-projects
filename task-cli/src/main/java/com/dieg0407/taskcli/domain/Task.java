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
    if (createdAt == null) {
      throw new IllegalArgumentException("CreatedAt field can't by null");
    }
    if (updatedAt == null) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Task other = (Task) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (status != other.status)
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    return true;
  }



  // ----------------------------------------------
  // endregion
}
