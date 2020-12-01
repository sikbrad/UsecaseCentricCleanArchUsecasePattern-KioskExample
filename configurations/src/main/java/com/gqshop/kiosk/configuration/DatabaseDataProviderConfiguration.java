package com.gqshop.kiosk.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.dataprovider.database.foodmenu.FoodMenuDatabaseMockDataProvider;

@Configuration
public class DatabaseDataProviderConfiguration {
	@Bean
	public FoodMenuDatabaseMockDataProvider foodMenuDatabaseMockDataProvider() {		
		ArrayList<FoodMenu> foodMenu = new ArrayList<FoodMenu>();
		
		//putting data for source checking
//		foodMenu.add(new FoodMenu("soup", "homemade soup"));
		
		return new FoodMenuDatabaseMockDataProvider(foodMenu);
	}
}
