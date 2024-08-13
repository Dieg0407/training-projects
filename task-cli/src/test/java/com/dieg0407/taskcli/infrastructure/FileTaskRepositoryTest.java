package com.dieg0407.taskcli.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import com.dieg0407.taskcli.domain.Task;
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
    final var newTask = new Task("some test description");
    final var generatedId = taskRepository.create(newTask);

    assertThat(generatedId).isNotNull();

    final List<String> savedLines = Files.readAllLines(FileTaskRepository.PATH).stream()
        .filter(l -> l.contains(String.valueOf(generatedId.id()) + "|")).collect(Collectors.toList());

    assertThat(savedLines).hasSize(1);
    assertThat(savedLines.get(0)).contains("some test description");
  }

  @Test
  void shouldUpdateTheNewEntry() throws Exception {
    final var newTask = new Task("a regular test");
    final var generatedId = taskRepository.create(newTask);

    assertThat(generatedId).isNotNull();

    newTask.updateDescription("a new description value");
    taskRepository.update(generatedId, newTask);

    final List<String> savedLines = Files.readAllLines(FileTaskRepository.PATH).stream()
        .filter(l -> l.contains(String.valueOf(generatedId.id()) + "|")).collect(Collectors.toList());

    assertThat(savedLines).hasSize(1);
    assertThat(savedLines.get(0)).contains("a new description value");
  }
}
