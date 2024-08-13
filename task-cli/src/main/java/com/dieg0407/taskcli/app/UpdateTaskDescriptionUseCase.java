package com.dieg0407.taskcli.app;

import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskRepository;

public class UpdateTaskDescriptionUseCase {
  private final TaskRepository taskRepository;

  public UpdateTaskDescriptionUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void execute(TaskId taskId, String newDescription) throws RuntimeException {
    final var task = taskRepository.findById(taskId)
        .orElseThrow(() -> new RuntimeException("No task id with value: " + taskId));

    task.updateDescription(newDescription);
    taskRepository.update(taskId, task);
  }
}
