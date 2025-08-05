use std::io;
use std::io::Read;

pub fn parse_markdown<T: Read>(mut reader: T) -> io::Result<()> {
    let mut buffer: [u8; 1024] = [0; 1024];

    loop {
        let bytes_read = reader.read(&mut buffer)?;
        if bytes_read == 0 {
            break;
        }
        let data = &buffer[0..bytes_read];

        // TODO: not all u8 slices are valid utf8 strings
        let data = String::from_utf8_lossy(data);

        println!("Read data: {}", data);
    }

    Ok(())
}
