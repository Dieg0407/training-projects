package com.dieg0407.taskcli.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.dieg0407.taskcli.domain.Task;
import com.dieg0407.taskcli.domain.TaskId;
import com.dieg0407.taskcli.domain.TaskRepository;
import com.dieg0407.taskcli.domain.TaskStatus;

public class FileTaskRepository implements TaskRepository {
  public static final Path PATH = Path.of("tasks");

  @Override
  public TaskId create(Task task) {
    try {
      final var taskId = new TaskId(generateNewId());
      task.updateId(taskId);

      final var serialized = this.serializeTask(task);
      Files.write(PATH, serialized.getBytes(), StandardOpenOption.APPEND);

      return taskId;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(TaskId taskId, Task incomingTask)
      throws RuntimeException, IllegalArgumentException {
    try {
      final var savedTasks = loadTasks().toArray(Task[]::new);

      for (var i = 0; i < savedTasks.length; i++) {
        if (taskId.id() != savedTasks[i].getId().orElse(new TaskId(-1)).id()) {
          continue;
        }
        incomingTask.updateId(taskId);
        savedTasks[i] = incomingTask;
        break;
      }

      final var serializedTasks =
          Stream.of(savedTasks).map(this::serializeTask).collect(Collectors.joining(""));

      Files.write(PATH, serializedTasks.getBytes(), StandardOpenOption.WRITE,
          StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public Optional<Task> findById(TaskId id) throws RuntimeException {
    try {
      return loadTasks().filter(task -> id.equals(task.getId().get())).findFirst();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Stream<Task> loadTasks() throws IOException {
    if (!Files.exists(PATH)) {
      Files.createFile(PATH);
    }
    return Files.readAllLines(PATH).stream().map(this::parseFromTask);
  }

  private long generateNewId() throws IOException {
    return loadTasks().map(Task::getId).filter(Optional::isPresent).map(Optional::get)
        .map(TaskId::id).max(Long::compare).orElse(0L) + 1;
  }

  private Task parseFromTask(String entry) {
    try {
      final var parts = entry.split("\\|");

      final var id = new TaskId(Long.parseLong(parts[0]));
      final var description = parts[1];
      final var status = TaskStatus.valueOf(parts[2]);
      final var createdAt = Instant.parse(parts[3]);
      final var updatedAt = Instant.parse(parts[4]);

      return new Task(id, description, status, createdAt, updatedAt);
    } catch (IndexOutOfBoundsException e) {
      throw new RuntimeException(
          "Entries in the file should follow the format <id>|<description>|<status>|<created date>|<updated date>",
          e);
    } catch (NumberFormatException e) {
      throw new RuntimeException("The id value couldn't be parsed", e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String serializeTask(Task task) {
    return String.format("%d|%s|%s|%s|%s\n", task.getId().get().id(), task.getDescription(),
        task.getStatus().name(), task.getCreatedAt().toString(), task.getUpdatedAt().toString());
  }
}
