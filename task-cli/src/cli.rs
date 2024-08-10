use clap::{arg, command, ArgMatches, Command};

use crate::{application::AddTaskUseCase, domain::TaskRepository};

const ADD: &'static str = "add";
const UPDATE: &'static str = "update";
const DELETE: &'static str = "delete";
const MARK_IN_PROGRESS: &'static str = "mark-in-progress";
const MARK_DONE: &'static str = "mark-done";
const LIST: &'static str = "list";
const LIST_DONE: &'static str = "done";
const LIST_TODO: &'static str = "todo";
const LIST_IN_PROGRESS: &'static str = "in-progress";

fn create_arguments_matcher() -> ArgMatches {
    command!()
        .subcommand(
            Command::new(ADD)
                .about("Will create a new task")
                .arg(arg!([task] "The task value to create")),
        )
        .subcommand(
            Command::new(UPDATE)
                .about("Updates a task by it's id")
                .arg(arg!([id] "The auto generated id of the task"))
                .arg(arg!([task] "The new task value")),
        )
        .subcommand(
            Command::new(DELETE)
                .about("Sets the task as deleted")
                .arg(arg!([id] "The auto generated id of the task")),
        )
        .subcommand(
            Command::new(MARK_IN_PROGRESS)
                .about("Sets the task as in progress")
                .arg(arg!([id] "The auto generated id of the task")),
        )
        .subcommand(
            Command::new(MARK_DONE)
                .about("Sets the task as completed")
                .arg(arg!([id] "The auto generated id of the task")),
        )
        .subcommand(Command::new(LIST).about("lists all tasks").subcommands([
            Command::new(LIST_DONE),
            Command::new(LIST_TODO),
            Command::new(LIST_IN_PROGRESS),
        ]))
        .get_matches()
}

pub struct TaskCli {
    matcher: ArgMatches,
    add_task_use_case: AddTaskUseCase,
}

impl TaskCli {
    pub fn new(task_repository: Box<dyn TaskRepository>) -> TaskCli {
        TaskCli {
            matcher: create_arguments_matcher(),
            add_task_use_case: AddTaskUseCase::new(task_repository),
        }
    }

    pub fn process_input(&self) -> i32 {
        if let Some(matches) = self.matcher.subcommand_matches(ADD) {
            let definition = matches.get_one::<String>("task").unwrap().clone();
            self.add_task_use_case.execute(definition)
        } else {
            0
        }
    }
}
