use cli::TaskCli;
use infrastructure::FileTaskRepository;

mod application;
mod domain;
mod infrastructure;

mod cli;

pub fn create_task_cli() -> TaskCli {
    TaskCli::new(Box::new(FileTaskRepository::new()))
}
