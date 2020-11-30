package com.gqshop.kiosk.dataprovider.database.foodmenu;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.cookingstaff_serving.get_menu.GetAllMenu;

public class FoodMenuDatabaseMockDataProvider implements GetAllMenu{
	private Collection<FoodMenu> foodMenuList;
	public FoodMenuDatabaseMockDataProvider(Collection<FoodMenu> foodMenuList) {
		this.foodMenuList = foodMenuList;
	}

	@Override
	public Collection<FoodMenu> getAllFoodMenu() {
		return foodMenuList;
	}

}
