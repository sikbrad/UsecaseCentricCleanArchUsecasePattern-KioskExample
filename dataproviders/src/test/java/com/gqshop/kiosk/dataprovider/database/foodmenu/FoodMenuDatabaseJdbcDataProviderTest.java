package com.gqshop.kiosk.dataprovider.database.foodmenu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class FoodMenuDatabaseJdbcDataProviderTest {
	
	private JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
	FoodMenu fakeMenu = new FoodMenu(UUID.randomUUID(), RandomString.make(), RandomString.make());	
	
	FoodMenuDatabaseJdbcDataProvider foodMenuDatabaseJdbcDataProvider = new FoodMenuDatabaseJdbcDataProvider(jdbcTemplate);

	@Test
	public void returnAllFoodMenu() {		
		List<FoodMenu> expected = givenOneFoodMenuFound_withQuery();
		List<FoodMenu> actual = foodMenuDatabaseJdbcDataProvider.getAll().stream().collect(Collectors.toList());
		assertThat(actual.size()).isEqualTo(expected.size());
	}

	@Test
	public void returnsFoodMenuWithId() {
		FoodMenu expected = givenOneFoodMenuFound_withQueryForObject();
		FoodMenu actual = foodMenuDatabaseJdbcDataProvider.getWithId(fakeMenu.getId());
		assertThat(actual.getId()).isEqualTo(expected.getId());
	}

	private FoodMenu givenOneFoodMenuFound_withQueryForObject() {
		FoodMenu foodMenu = new FoodMenu(this.fakeMenu);		
		when(this.jdbcTemplate.queryForObject(Mockito.anyString(),
				ArgumentMatchers.<RowMapper<FoodMenu>>any(),
				Mockito.anyString())).thenReturn(foodMenu);		
		return foodMenu;
	}
	
	private List<FoodMenu> givenOneFoodMenuFound_withQuery() {
		List<FoodMenu> allFoodMenu = new ArrayList<FoodMenu>();
		allFoodMenu.add(new FoodMenu(fakeMenu.getName(),fakeMenu.getDescription()));
		allFoodMenu.add(new FoodMenu(fakeMenu.getName(),fakeMenu.getDescription()));
		when(this.jdbcTemplate.query(Mockito.anyString(),
				ArgumentMatchers.<RowMapper<FoodMenu>>any()))
				.thenReturn(allFoodMenu);		
		return allFoodMenu;
	}
	
	//if using response without response mapper
//	private Map<String, Object> makeFoodMenuResponse(FoodMenu foodMenu) {
//		Map<String, Object> queryMap = new HashMap<>();
//		queryMap.put("id", foodMenu.getId());
//		queryMap.put("name", foodMenu.getName());
//		queryMap.put("description", foodMenu.getDescription());        
//		return queryMap;
//	}
}
