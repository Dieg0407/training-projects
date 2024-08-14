package com.dieg0407.taskcli.cli;

import java.util.concurrent.Callable;
import com.dieg0407.taskcli.app.MarkTaskAsDoneUseCase;
import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.infrastructure.DI;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "mark-done")
public class MarkAsDoneCommand implements Callable<Integer> {
  private final MarkTaskAsDoneUseCase markTaskAsDoneUseCase = DI.MARK_TASK_AS_DONE_USE_CASE;

  @Parameters(index = "0", description = "numeric task id")
  private int id;

  @Override
  public Integer call() throws Exception {
    try {
      markTaskAsDoneUseCase.execute(new TaskId(id));
      return Codes.SUCCESS;
    } catch (Exception e) {
      System.err.println(e);
      return Codes.ERROR;
    }
  }
}

