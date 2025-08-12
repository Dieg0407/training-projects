use std::io;
use std::io::Read;

pub fn parse_markdown(reader: Box<dyn Read>) -> io::Result<()> {
    let mut buffer: [u8; 1024] = [0; 1024];
    let line_reader = LinesReader::new(reader, &mut buffer);

    for line in line_reader {
        print!("{}", line);
    }

    Ok(())
}

struct LinesReader<'a> {
    reader: Box<dyn Read>,
    buffer: &'a mut [u8],
    current_position: usize,
    read_bytes: usize,
}

impl<'a> LinesReader<'a> {
    pub fn new(reader: Box<dyn Read>, buffer: &'a mut [u8]) -> LinesReader<'a> {
        LinesReader {
            reader,
            buffer,
            current_position: 0,
            read_bytes: 0,
        }
    }
}

impl<'a> Iterator for LinesReader<'a> {
    type Item = String;

    fn next(&mut self) -> Option<Self::Item> {
        let mut line: Vec<u8> = vec![];
        loop {
            let curr_pos = self.current_position.clone();
            for i in curr_pos..self.read_bytes {
                line.push(self.buffer[i]);
                self.current_position = i;
                if self.buffer[i] == b'\n' {
                    self.current_position += 1;
                    return Some(String::from_utf8(line).unwrap());
                }
            }

            self.current_position = 0;
            self.read_bytes = self.reader.as_mut().read(self.buffer).unwrap();

            if self.read_bytes == 0 {
                return None;
            }
        }
    }
}
