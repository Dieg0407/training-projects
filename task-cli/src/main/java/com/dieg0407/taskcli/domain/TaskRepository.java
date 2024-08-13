package com.dieg0407.taskcli.domain;

public interface TaskRepository {
  /**
   * Creates a new id and saves a task
   * @param task the raw task values, if it has an id then it's ignored
   * @return the generated TaskId
   * @throws RuntimeException if there's an internal issue
   */
  TaskId create(Task task) throws RuntimeException;

  /**
   * This method will replace the task with the provided id
   * with the values in the new Task.
   * 
   * @param taskId The identifier to modify
   * @param incomingTask The new values holder
   * @throws RuntimeException if there's an access issue
   * @throws IllegalArgumentException if the id doesn't exists
   */
  void update(TaskId taskId, Task incomingTask) throws RuntimeException, IllegalArgumentException;
}
