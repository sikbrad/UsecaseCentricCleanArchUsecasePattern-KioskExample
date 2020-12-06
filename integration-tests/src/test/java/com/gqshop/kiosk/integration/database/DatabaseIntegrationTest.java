package com.gqshop.kiosk.integration.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class DatabaseIntegrationTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment env;
	
	@Test
	public void runsomething() {
		logger.info(String.format("Property mode : %s", env.getProperty("spring.profiles")));
		assert(true);
	}
}
