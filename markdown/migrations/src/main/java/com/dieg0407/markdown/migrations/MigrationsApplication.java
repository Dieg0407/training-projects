package com.dieg0407.markdown.migrations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MigrationsApplication implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(MigrationsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MigrationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Migrations ran");
	}

}
