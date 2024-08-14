package com.dieg0407.taskcli.app;

import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskRepository;

public class DeleteTaskUseCase {
  private final TaskRepository taskRepository;

  public DeleteTaskUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void execute(TaskId taskId) {
    this.taskRepository.delete(taskId);
  }
}
