package com.gqshop.kiosk.dataprovider.database.foodmenu;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetAllFoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithId;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithName;

@Profile("default")
public class FoodMenuDatabaseMockDataProvider implements GetAllFoodMenu, GetFoodMenuWithId, GetFoodMenuWithName {
	private Collection<FoodMenu> foodMenuList;

	public FoodMenuDatabaseMockDataProvider(Collection<FoodMenu> foodMenuList) {
		this.foodMenuList = foodMenuList;
	}

	@Override
	public FoodMenu getWithId(UUID id) {
		Collection<FoodMenu> searchResult = foodMenuList.stream().filter(foodmenu -> foodmenu.getId().equals(id)).collect(Collectors.toList());
		
		if(searchResult.size() ==0) return null;
		
		return searchResult.iterator().next();
	}

	@Override
	public Collection<FoodMenu> getAll() {
		return foodMenuList;
	}

	@Override
	public FoodMenu getWithName(String name) {
		Collection<FoodMenu> searchResult = foodMenuList.stream().filter(foodmenu -> foodmenu.getName().equals(name)).collect(Collectors.toList());
		
		if(searchResult.size() ==0) return null;
		
		return searchResult.iterator().next();
	}
}
