use task_cli::create_task_cli;

fn main() {
    let cli = create_task_cli();
    let exit_code = cli.process_input();

    std::process::exit(exit_code);
}
