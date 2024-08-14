package com.dieg0407.taskcli.cli;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "task-cli",
    subcommands = {AddSubCommand.class, UpdateSubCommand.class, DeleteSubCommand.class,
        ListSubCommand.class, MarkAsDoneCommand.class, MarkInProgressCommand.class})
public class RootCommand implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.err.println("You need to call a sub command");
    return Codes.SUCCESS;
  }

}
