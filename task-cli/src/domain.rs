use std::error::Error;
use std::fmt::Display;

#[derive(Clone)]
pub enum Status {
    Done,
    InProgress,
    Todo,
}

impl Display for Status {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match *self {
            Status::Done => write!(f, "Done"),
            Status::InProgress => write!(f, "InProgress"),
            Status::Todo => write!(f, "Todo"),
        }
    }
}

pub struct Task {
    pub id: Option<TaskId>,
    pub definition: String,
    pub status: Status,
}

#[derive(Debug, Clone)]
pub struct TaskId {
    pub id: i64,
}

impl Task {
    pub fn new(definition: String, status: Status) -> Task {
        Task {
            id: None,
            definition,
            status,
        }
    }
}

pub trait TaskRepository {
    fn save(&self, task: Task) -> Result<Task, Box<dyn Error>>;
}
