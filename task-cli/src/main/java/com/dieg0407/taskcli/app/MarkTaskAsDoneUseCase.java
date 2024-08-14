package com.dieg0407.taskcli.app;

import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskRepository;

public class MarkTaskAsDoneUseCase {
  private TaskRepository taskRepository;

  public MarkTaskAsDoneUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void execute(TaskId taskId) {
    final var task = taskRepository.findById(taskId)
        .orElseThrow(() -> new RuntimeException("There's no task with id " + taskId.id()));

    task.markAsDone();
    taskRepository.update(taskId, task);
  }
}
