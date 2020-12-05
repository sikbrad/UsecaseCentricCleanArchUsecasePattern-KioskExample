package com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu;

import java.util.UUID;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetFoodMenuWithId {
	FoodMenu getWithId(UUID id);
}
