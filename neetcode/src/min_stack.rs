#[allow(dead_code)]
struct MinStack {
    content: Vec<(i32, i32)>,
}

#[allow(dead_code)]
impl MinStack {
    fn new() -> Self {
        MinStack { content: vec![] }
    }

    fn push(&mut self, val: i32) {
        if self.content.is_empty() {
            self.content.push((val, val));
            return;
        }
        let prev_val = self.content.last().unwrap();
        let curr_min = if prev_val.1 > val { val } else { prev_val.1 };

        self.content.push((val, curr_min));
    }

    fn pop(&mut self) {
        self.content.remove(self.content.len() - 1 as usize);
    }

    fn top(&self) -> i32 {
        self.content.last().unwrap_or(&(0, 0)).0
    }

    fn get_min(&self) -> i32 {
        self.content.last().unwrap_or(&(0, 0)).1
    }
}
