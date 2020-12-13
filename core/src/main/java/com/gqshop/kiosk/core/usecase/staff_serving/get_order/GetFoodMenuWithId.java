package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import java.util.UUID;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetFoodMenuWithId {
	FoodMenu getWithId(String id);
}
