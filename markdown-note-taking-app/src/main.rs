use std::fs::File;
use std::io;

mod parser;

fn main() -> io::Result<()> {
    let file: File = File::open("README.md")?;
    let _ = parser::parse_markdown(Box::new(file));

    return Ok(());
}
