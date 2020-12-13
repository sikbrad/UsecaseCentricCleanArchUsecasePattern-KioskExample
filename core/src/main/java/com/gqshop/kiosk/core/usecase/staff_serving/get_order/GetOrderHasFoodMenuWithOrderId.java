package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.valueobject.OrderHasFoodMenu;

public interface GetOrderHasFoodMenuWithOrderId {
	Collection<OrderHasFoodMenu> getOrderHasFoodMenuWithOrderId(String orderId);
}
