package com.dapg.handmade.marketplace.engine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class EngineApplicationTests {

	@Test
	void contextLoads() {}

	@Test
	void verifyModules() {
		ApplicationModules.of(EngineApplication.class).verify();
	}

}
