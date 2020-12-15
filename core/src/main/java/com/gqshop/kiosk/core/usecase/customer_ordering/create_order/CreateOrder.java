package com.gqshop.kiosk.core.usecase.customer_ordering.create_order;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;

public interface CreateOrder {
	public Order createOrder(Collection<FoodMenu> foodMenus);
}
