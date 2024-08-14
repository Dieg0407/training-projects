package com.dieg0407.taskcli;

import com.dieg0407.taskcli.cli.RootCommand;
import picocli.CommandLine;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    final var exitCode = new CommandLine(new RootCommand()).execute(args);

    System.exit(exitCode);
  }
}
