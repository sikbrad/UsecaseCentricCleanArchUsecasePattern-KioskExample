package com.gqshop.kiosk.integration.database.customer_ordering;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.assertj.core.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.dataprovider.database.foodmenu.FoodMenuDatabaseMockDataProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CustomerOrderingEntrypointRestIntegrationTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FoodMenuDatabaseMockDataProvider foodMenuDatabaseMockDataProvider;

	@Autowired
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase;

	@Autowired
	private MockMvc mvc;

	public String apiUrl;

	@Autowired
	Environment env;

	@Before
	public void setUp() throws Exception {
		logger.info("called test setup");

		apiUrl = String.format("http://localhost:%s/api", env.getProperty("server.port"));

	}

	@Test
	public void returnsApiInfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.version").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.version").isNotEmpty());
	}

	@Test
	public void givenTwoMenu_whenGetFoodMenus_thenReturnsTwo() throws Exception {
		final String[] foodMenuNames = new String[] { "kimchi", "tongdak" };
		givenFoodMenusExist(foodMenuNames);
		mvc.perform(MockMvcRequestBuilders.get(String.format("%s/foodmenu", apiUrl))).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(foodMenuNames[0])))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(foodMenuNames[1])));
	}
	
	
	@Test
	public void givenTwoMenu_whenGetFoodMenuWithProperId_thenReturnsOne() throws Exception {
		final String[] foodMenuNames = new String[] { "kimchi", "tongdak" };
		givenFoodMenusExist(foodMenuNames);
		
		List<String> foodMenuIds = foodMenuDatabaseMockDataProvider.getAll().stream().map(x->x.getId().toString()).collect(Collectors.toList());
		
		mvc.perform(MockMvcRequestBuilders.get(String.format("%s/foodmenu/%s", apiUrl, foodMenuIds.get(1)))).andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(foodMenuIds.get(1))));
	}

	
	private void givenFoodMenusExist(String[] foodNames) {
		Collection<FoodMenu> foodMenuList = new ArrayList<FoodMenu>();
		List<Object> foodNamesList = Arrays.asList(foodNames);
		foodNamesList.forEach(foodName -> {
			foodMenuList.add(new FoodMenu((String) foodName, RandomString.make(), RandomString.make()));
		});
		try {
			FieldUtils.writeDeclaredField(foodMenuDatabaseMockDataProvider, "foodMenuList", foodMenuList, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
