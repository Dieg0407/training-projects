package com.dieg0407.taskcli.domain;

import java.util.Optional;
import java.util.List;

public interface TaskRepository {
  /**
   * Creates a new id and saves a task
   * 
   * @param task the raw task values, if it has an id then it's ignored
   * @return the generated TaskId
   * @throws RuntimeException if there's an internal issue
   */
  TaskId create(Task task) throws RuntimeException;

  /**
   * This method will replace the task with the provided id with the values in the new Task.
   * 
   * @param taskId The identifier to modify
   * @param incomingTask The new values holder
   * @throws RuntimeException if there's an access issue
   * @throws IllegalArgumentException if the id doesn't exists
   */
  void update(TaskId taskId, Task incomingTask) throws RuntimeException, IllegalArgumentException;

  /**
   * Will return a task or an empty optional depending on whether or not the id exists
   * 
   * @param id
   * @return
   * @throws RuntimeException
   */
  Optional<Task> findById(TaskId id) throws RuntimeException;

  /**
   * 
   * @param id
   * @throws RuntimeException
   */
  void delete(TaskId id) throws RuntimeException;

  /**
   * 
   * @return
   * @throws RuntimeException
   */
  List<Task> fetch() throws RuntimeException;

  /**
   * 
   * @param status
   * @return
   * @throws RuntimeException
   */
  List<Task> fetchByStatus(TaskStatus status) throws RuntimeException;
}
