package com.dieg0407.taskcli.cli;

import java.util.concurrent.Callable;
import com.dieg0407.taskcli.app.ListAllTasksUseCase;
import com.dieg0407.taskcli.app.ListTaskByStatusUseCase;
import com.dieg0407.taskcli.domain.Task;
import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskStatus;
import com.dieg0407.taskcli.infrastructure.DI;
import picocli.CommandLine.Command;

@Command(name = "list")
public class ListSubCommand implements Callable<Integer> {
  private final ListAllTasksUseCase listAllTasksUseCase = DI.LIST_ALL_TASKS_USE_CASE;
  private final ListTaskByStatusUseCase listTaskByStatusUseCase = DI.LIST_TASK_BY_STATUS_USE_CASE;

  @Override
  public Integer call() throws Exception {
    try {
      final var tasks = listAllTasksUseCase.execute();
      tasks.forEach(this::printTask);
      return Codes.SUCCESS;
    } catch (Exception e) {
      return Codes.ERROR;
    }
  }

  @Command(name = "done")
  public Integer listDone() {
    try {
      final var tasks = listTaskByStatusUseCase.execute(TaskStatus.DONE);
      tasks.forEach(this::printTask);
      return Codes.SUCCESS;
    } catch (Exception e) {
      return Codes.ERROR;
    }
  }

  @Command(name = "todo")
  public Integer listTodo() {
    try {
      final var tasks = listTaskByStatusUseCase.execute(TaskStatus.TODO);
      tasks.forEach(this::printTask);
      return Codes.SUCCESS;
    } catch (Exception e) {
      return Codes.ERROR;
    }
  }

  @Command(name = "in-progress")
  public Integer listInProgress() {
    try {
      final var tasks = listTaskByStatusUseCase.execute(TaskStatus.IN_PROGRESS);
      tasks.forEach(this::printTask);
      return Codes.SUCCESS;
    } catch (Exception e) {
      return Codes.ERROR;
    }
  }

  private void printTask(Task task) {
    final var format = String.format("%d -> %s , %s", task.getId().map(TaskId::id).orElse(-1L),
        task.getDescription(), task.getStatus().name());

    System.err.println(format);
  }

}
