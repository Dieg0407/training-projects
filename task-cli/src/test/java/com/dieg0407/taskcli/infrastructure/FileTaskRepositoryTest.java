package com.dieg0407.taskcli.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import com.dieg0407.taskcli.domain.Task;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.file.Files;

@TestInstance(Lifecycle.PER_CLASS)
public class FileTaskRepositoryTest {
  final FileTaskRepository taskRepository = new FileTaskRepository();


  @AfterAll
  void afterAll() throws Exception {
    Files.delete(FileTaskRepository.PATH);
  }

  @Test
  void shouldSaveNewEntryInFile() {
    final var newTask = new Task("some test description");
    final var generatedId = taskRepository.create(newTask);

    assertThat(generatedId).isNotNull();
  }
}
