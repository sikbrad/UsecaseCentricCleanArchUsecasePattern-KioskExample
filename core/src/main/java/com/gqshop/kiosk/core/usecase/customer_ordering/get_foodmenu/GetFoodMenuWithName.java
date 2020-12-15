package com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetFoodMenuWithName {
	FoodMenu getWithName(String name);
}
