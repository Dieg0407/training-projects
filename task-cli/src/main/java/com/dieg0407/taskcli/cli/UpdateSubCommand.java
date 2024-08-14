package com.dieg0407.taskcli.cli;

import java.util.concurrent.Callable;
import com.dieg0407.taskcli.app.UpdateTaskDescriptionUseCase;
import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.infrastructure.DI;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "update")
public class UpdateSubCommand implements Callable<Integer> {
  private final UpdateTaskDescriptionUseCase updateTaskDescriptionUseCase =
      DI.UPDATE_TASK_DESCRIPTION_USE_CASE;

  @Parameters(index = "0", description = "numeric task id")
  private int id;

  @Parameters(index = "1", description = "new description")
  private String description;

  @Override
  public Integer call() throws Exception {
    try {
      updateTaskDescriptionUseCase.execute(new TaskId(id), description);
      return Codes.SUCCESS;
    } catch (Exception e) {
      System.err.println(e);
      return Codes.ERROR;
    }
  }

}
