package com.gqshop.kiosk.dataprovider.database.foodmenu;

import java.util.Collection;
import java.util.stream.Collectors;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.GetAllMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.GetMenuDetail;

public class FoodMenuDatabaseMockDataProvider implements GetAllMenu, GetMenuDetail {
	private Collection<FoodMenu> foodMenuList;

	public FoodMenuDatabaseMockDataProvider(Collection<FoodMenu> foodMenuList) {
		this.foodMenuList = foodMenuList;
	}

	@Override
	public Collection<FoodMenu> getAllFoodMenu() {
		return foodMenuList;
	}

	@Override
	public Collection<FoodMenu> searchWithFoodName(String foodname) {
		Collection<FoodMenu> searchResult = foodMenuList.stream().filter(foodmenu -> foodmenu.getName().equals(foodname)).collect(Collectors.toList());
		return searchResult;
	}

}
