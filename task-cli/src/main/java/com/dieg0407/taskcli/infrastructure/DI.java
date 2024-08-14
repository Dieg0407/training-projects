package com.dieg0407.taskcli.infrastructure;

import com.dieg0407.taskcli.app.AddTaskUseCase;
import com.dieg0407.taskcli.app.DeleteTaskUseCase;
import com.dieg0407.taskcli.app.ListAllTasksUseCase;
import com.dieg0407.taskcli.app.ListTaskByStatusUseCase;
import com.dieg0407.taskcli.app.MarkTaskAsDoneUseCase;
import com.dieg0407.taskcli.app.MarkTaskInProgressUseCase;
import com.dieg0407.taskcli.app.UpdateTaskDescriptionUseCase;
import com.dieg0407.taskcli.domain.TaskRepository;

public final class DI {
  public static final TaskRepository TASK_REPOSITORY = new FileTaskRepository();
  public static final AddTaskUseCase ADD_TASK_USE_CASE = new AddTaskUseCase(TASK_REPOSITORY);
  public static final DeleteTaskUseCase DELETE_TASK_USE_CASE =
      new DeleteTaskUseCase(TASK_REPOSITORY);
  public static final ListAllTasksUseCase LIST_ALL_TASKS_USE_CASE =
      new ListAllTasksUseCase(TASK_REPOSITORY);
  public static final ListTaskByStatusUseCase LIST_TASK_BY_STATUS_USE_CASE =
      new ListTaskByStatusUseCase(TASK_REPOSITORY);
  public static final UpdateTaskDescriptionUseCase UPDATE_TASK_DESCRIPTION_USE_CASE =
      new UpdateTaskDescriptionUseCase(TASK_REPOSITORY);
  public static final MarkTaskInProgressUseCase MARK_TASK_IN_PROGRESS_USE_CASE =
      new MarkTaskInProgressUseCase(TASK_REPOSITORY);
  public static final MarkTaskAsDoneUseCase MARK_TASK_AS_DONE_USE_CASE =
      new MarkTaskAsDoneUseCase(TASK_REPOSITORY);
}
