package com.dieg0407.taskcli.cli;

import java.util.concurrent.Callable;
import com.dieg0407.taskcli.app.AddTaskUseCase;
import com.dieg0407.taskcli.infrastructure.DI;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "add")
public class AddSubCommand implements Callable<Integer> {
  private final AddTaskUseCase addTaskUseCase = DI.ADD_TASK_USE_CASE;

  @Parameters(index = "0", description = "task description")
  private String description;

  @Override
  public Integer call() throws Exception {
    try {
      addTaskUseCase.execute(this.description);
      return Codes.SUCCESS;
    } catch (Exception e) {
      System.err.println(e);
      return Codes.ERROR;
    }
  }

}
