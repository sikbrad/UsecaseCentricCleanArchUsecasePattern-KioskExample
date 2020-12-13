package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetFoodMenuWithOrderId {
	Collection<FoodMenu> getFoodMenuWithOrderId(String orderId);	
}
