package com.dieg0407.taskcli.app;

import java.util.List;
import com.dieg0407.taskcli.domain.Task;
import com.dieg0407.taskcli.domain.TaskRepository;
import com.dieg0407.taskcli.domain.TaskStatus;

public class ListTaskByStatusUseCase {
  private final TaskRepository taskRepository;

  public ListTaskByStatusUseCase(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> execute(TaskStatus status) {
    return this.taskRepository.fetchByStatus(status);
  }

}
