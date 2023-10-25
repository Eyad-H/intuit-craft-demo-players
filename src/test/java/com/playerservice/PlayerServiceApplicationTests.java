package com.playerservice;

import com.playerservice.controller.PlayerControllerTest;
import com.playerservice.utils.CsvValidationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CsvValidationTest.class, PlayerControllerTest.class})
class PlayerServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
