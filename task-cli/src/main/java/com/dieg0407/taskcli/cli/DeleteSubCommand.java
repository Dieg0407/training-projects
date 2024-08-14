package com.dieg0407.taskcli.cli;

import java.util.concurrent.Callable;
import com.dieg0407.taskcli.app.DeleteTaskUseCase;
import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.infrastructure.DI;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "delete")
public class DeleteSubCommand implements Callable<Integer> {
  private DeleteTaskUseCase deleteTaskUseCase = DI.DELETE_TASK_USE_CASE;

  @Parameters(index = "0", description = "the task id to remove")
  private long id = 0;


  @Override
  public Integer call() throws Exception {
    try {
      deleteTaskUseCase.execute(new TaskId(id));
      return Codes.SUCCESS;
    } catch (Exception e) {
      System.err.println(e);
      return Codes.ERROR;
    }
  }

}
