use std::error::Error;
use std::fs::OpenOptions;
use std::io::BufRead;
use std::io::BufReader;
use std::io::Write;

use crate::domain::Task;
use crate::domain::TaskId;
use crate::domain::TaskRepository;

const FILE_PATH: &'static str = "tasks.csv";

pub struct FileTaskRepository;

impl Task {
    pub fn to_csv_format(&self) -> String {
        let id = self.id.clone().map(|tid| tid.id).unwrap_or(0);
        let definition = &self.definition;
        let status = &self.status;

        format!("{}|{}|{}", id, definition, status)
    }
}

impl FileTaskRepository {
    pub fn new() -> FileTaskRepository {
        FileTaskRepository {}
    }

    fn create(&self, mut task: Task) -> Result<Task, Box<dyn Error>> {
        let lines: Vec<String> = {
            let file = OpenOptions::new()
                .read(true)
                .write(true)
                .append(true)
                .create(true)
                .open(FILE_PATH)?;

            let reader = BufReader::new(file);
            reader.lines().collect::<Result<_, _>>()?
        };

        let id_value: i64 = lines
            .last()
            .map(|l| {
                if let Some(first) = l.clone().split("|").next() {
                    first.parse().unwrap_or(1) + 1
                } else {
                    1
                }
            })
            .unwrap_or(1);
        let id = TaskId { id: id_value };
        task.id = Some(id);

        let new_line = task.to_csv_format();
        {
            let mut file = OpenOptions::new()
                .write(true)
                .append(true)
                .open(FILE_PATH)?;

            writeln!(file, "{}", new_line)?;
        }

        Ok(task)
    }

    fn update(&self, task: Task) -> Result<Task, Box<dyn Error>> {
        Ok(task)
    }
}

impl TaskRepository for FileTaskRepository {
    fn save(&self, task: Task) -> Result<Task, Box<dyn Error>> {
        if task.id.is_none() {
            self.create(task)
        } else {
            self.update(task)
        }
    }
}
