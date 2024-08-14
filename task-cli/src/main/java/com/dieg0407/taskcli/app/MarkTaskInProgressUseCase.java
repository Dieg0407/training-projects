package com.dieg0407.taskcli.app;

import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskRepository;

public class MarkTaskInProgressUseCase {
  private TaskRepository taskRepository;

  public MarkTaskInProgressUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public void execute(TaskId taskId) {
    final var task = taskRepository.findById(taskId)
        .orElseThrow(() -> new RuntimeException("There's no task with id " + taskId.id()));

    task.markInProgress();
    taskRepository.update(taskId, task);
  }
}
