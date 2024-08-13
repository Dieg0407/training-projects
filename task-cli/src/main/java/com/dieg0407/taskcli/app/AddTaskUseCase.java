package com.dieg0407.taskcli.app;

import com.dieg0407.taskcli.domain.Task;
import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskRepository;

public class AddTaskUseCase {
  private final TaskRepository taskRepository;

  public AddTaskUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskId execute(final String description) {
    var task = new Task(description);
    var taskId = taskRepository.create(task);

    return taskId;
  }
}
