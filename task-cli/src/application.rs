use crate::domain::Status;
use crate::domain::Task;
use crate::domain::TaskRepository;

pub struct AddTaskUseCase {
    task_repository: Box<dyn TaskRepository>,
}

impl AddTaskUseCase {
    pub fn new(task_repository: Box<dyn TaskRepository>) -> AddTaskUseCase {
        AddTaskUseCase { task_repository }
    }

    pub fn execute(&self, definition: String) -> i32 {
        let task = Task::new(definition, Status::Todo);
        let task = self.task_repository.save(task);

        match task {
            Ok(task) => {
                eprintln!("Task saved with id {:?}", task.id);
                0
            }
            Err(_) => 1,
        }
    }
}
