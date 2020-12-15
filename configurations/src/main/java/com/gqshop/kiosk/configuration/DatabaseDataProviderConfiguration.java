package com.gqshop.kiosk.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.dataprovider.database.foodmenu.FoodMenuDatabaseJdbcDataProvider;
import com.gqshop.kiosk.dataprovider.database.foodmenu.FoodMenuDatabaseMockDataProvider;
import com.gqshop.kiosk.dataprovider.database.order.OrderDatabaseJdbcProvider;
import com.gqshop.kiosk.dataprovider.database.order.OrderDatabaseMockProvider;


@Configuration
public class DatabaseDataProviderConfiguration {
	
	// MOCK
	@Bean
	@Profile("default")
	public FoodMenuDatabaseMockDataProvider foodMenuDatabaseMockDataProvider() {		
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		
		//putting data for source checking
		foodMenu.add(new FoodMenu("soup", "homemade soup",null));
		foodMenu.add(new FoodMenu("ramen", "shin ramen noodle",null));
		
		return new FoodMenuDatabaseMockDataProvider(foodMenu);
	}
	
	// JDBC
	@Bean
	@Profile("integrationtest")
	public FoodMenuDatabaseJdbcDataProvider foodMenuDatabaseJdbcDataProvider(JdbcTemplate jdbcTemplate) {		
		return new FoodMenuDatabaseJdbcDataProvider(jdbcTemplate);
	}
	

	// MOCK
	@Bean
	@Profile("default")
	public OrderDatabaseMockProvider orderDatabaseMockDataProvider() {		
		ArrayList<Order> orders = new ArrayList<Order>();	
		return new OrderDatabaseMockProvider(orders);
	}
	
	// JDBC
	@Bean
	@Profile("integrationtest")
	public OrderDatabaseJdbcProvider orderDatabaseJdbcDataProvider(JdbcTemplate jdbcTemplate) {		
		return new OrderDatabaseJdbcProvider(jdbcTemplate);
	}
}
