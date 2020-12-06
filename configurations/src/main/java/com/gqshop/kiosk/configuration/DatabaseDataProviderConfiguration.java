package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gqshop.kiosk.dataprovider.database.foodmenu.FoodMenuDatabaseJdbcDataProvider;

@Configuration
public class DatabaseDataProviderConfiguration {
	
	// MOCK
//	@Bean
//	public FoodMenuDatabaseMockDataProvider foodMenuDatabaseMockDataProvider() {		
//		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
//		
//		//putting data for source checking
//		foodMenu.add(new FoodMenu("soup", "homemade soup"));
//		foodMenu.add(new FoodMenu("ramen", "shin ramen noodle"));
//		
//		return new FoodMenuDatabaseMockDataProvider(foodMenu);
//	}
	
	// JDBC
	@Bean
	public FoodMenuDatabaseJdbcDataProvider foodMenuDatabaseJdbcDataProvider(JdbcTemplate jdbcTemplate) {		
//		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
//		
//		//putting data for source checking
//		foodMenu.add(new FoodMenu("soup", "homemade soup"));
//		foodMenu.add(new FoodMenu("ramen", "shin ramen noodle"));
		
		
		
		return new FoodMenuDatabaseJdbcDataProvider(jdbcTemplate);
	}
}
