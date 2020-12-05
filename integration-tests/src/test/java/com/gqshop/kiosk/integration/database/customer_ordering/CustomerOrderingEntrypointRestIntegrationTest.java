package com.gqshop.kiosk.integration.database.customer_ordering;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;

//@RunWith(SpringRunner.class)
//@WebMvcTest(CustomerOrderingEntrypointRest.class)

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CustomerOrderingEntrypointRestIntegrationTest {
	
	@Autowired
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase;

	@Autowired
    private MockMvc mvc;
	
	public String apiUrl;
	
	@Autowired 
	Environment env;
    
    @Before
    public void setUp() throws Exception{
    	System.out.println("called test setup");
    	
    	apiUrl = String.format("http://localhost:%s/api",
    			env.getProperty("server.port"));
    	
    }
    
    @Test
    public void getsApiInfo() throws Exception{
    	mvc.perform(MockMvcRequestBuilders.get(apiUrl))
    	.andExpect(status().isOk());
    }
    
}
