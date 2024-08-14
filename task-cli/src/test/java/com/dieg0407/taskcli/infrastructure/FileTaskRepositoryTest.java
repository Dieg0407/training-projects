package com.dieg0407.taskcli.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import com.dieg0407.taskcli.domain.Task;
import com.dieg0407.taskcli.domain.TaskId;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@TestInstance(Lifecycle.PER_CLASS)
public class FileTaskRepositoryTest {
  final FileTaskRepository taskRepository = new FileTaskRepository();

  @AfterAll
  void afterAll() throws Exception {
    Files.delete(FileTaskRepository.PATH);
  }

  @Test
  void shouldSaveNewEntryInFile() throws Exception {
    final var holder = createTask("test shouldSaveNewEntryInFile");
    final var generatedId = holder.id();

    assertThat(generatedId).isNotNull();

    final List<String> savedLines = Files.readAllLines(FileTaskRepository.PATH).stream()
        .filter(l -> l.contains(String.valueOf(generatedId.id()) + "|"))
        .collect(Collectors.toList());

    assertThat(savedLines).hasSize(1);
    assertThat(savedLines.get(0)).contains("test shouldSaveNewEntryInFile");
  }

  @Test
  void shouldUpdateTheNewEntry() throws Exception {
    final var holder = createTask("test shouldUpdateTheNewEntry");
    final var generatedId = holder.id();
    final var newTask = holder.newTask();

    newTask.updateDescription("a new description value");
    taskRepository.update(generatedId, newTask);

    final List<String> savedLines = Files.readAllLines(FileTaskRepository.PATH).stream()
        .filter(l -> l.contains(String.valueOf(generatedId.id()) + "|"))
        .collect(Collectors.toList());

    assertThat(savedLines).hasSize(1);
    assertThat(savedLines.get(0)).contains("a new description value");
  }

  @Test
  void shouldFindASavedTaskEntry() {
    final var holder = createTask("test shouldFindASavedTaskEntry");
    final var generatedId = holder.id();
    final var newTask = holder.newTask();

    final var task = taskRepository.findById(generatedId);
    assertThat(task).isPresent();
    assertThat(task.get()).isEqualTo(newTask);
  }

  @Test
  void shouldDeleteTaskById() throws Exception {
    final var holder = createTask("test delete");
    final var generatedId = holder.id();

    taskRepository.delete(generatedId);
    final List<String> savedLines = Files.readAllLines(FileTaskRepository.PATH).stream()
        .filter(l -> l.contains(String.valueOf(generatedId.id()) + "|"))
        .collect(Collectors.toList());

    assertThat(savedLines).isEmpty();
  }

  @Test
  void shouldListAllTasks() throws Exception {
    final var holder1 = createTask("list task 1");
    final var holder2 = createTask("list task 2");
    final var holder3 = createTask("list task 3");

    final var tasks = taskRepository.fetch();
    assertThat(tasks).contains(holder1.newTask(), holder2.newTask(), holder3.newTask());

  }

  private CreateTaskHolder createTask(String description) {
    final var newTask = new Task(description);
    final var generatedId = taskRepository.create(newTask);

    assertThat(generatedId).isNotNull();

    return new CreateTaskHolder(newTask, generatedId);
  }

  record CreateTaskHolder(Task newTask, TaskId id) {
  }
}
