use std::fs::File;
use std::io;

mod parser;

fn main() -> io::Result<()> {
    let mut file = File::open("README.md")?;
    let _ = parser::parse_markdown(&mut file);

    return Ok(());
}
