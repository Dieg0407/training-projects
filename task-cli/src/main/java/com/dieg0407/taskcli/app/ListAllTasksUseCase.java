package com.dieg0407.taskcli.app;

import java.util.List;
import com.dieg0407.taskcli.domain.Task;
import com.dieg0407.taskcli.domain.TaskRepository;

public class ListAllTasksUseCase {
  private final TaskRepository taskRepository;

  public ListAllTasksUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> execute() {
    return this.taskRepository.fetch();
  }
}
