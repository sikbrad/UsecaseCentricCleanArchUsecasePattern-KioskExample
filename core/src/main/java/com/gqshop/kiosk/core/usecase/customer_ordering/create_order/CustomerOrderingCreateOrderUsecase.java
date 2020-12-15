package com.gqshop.kiosk.core.usecase.customer_ordering.create_order;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;

public class CustomerOrderingCreateOrderUsecase {
	private final CreateOrder createOrder;

	public CustomerOrderingCreateOrderUsecase(CreateOrder createOrder) {
		super();
		this.createOrder = createOrder;
	}	

	public Order createOrder(Collection<FoodMenu> foodMenus) {
		
		if(foodMenus.size() == 0) {
			throw new NoFoodMenuInNewOrderException();
		}
		
		return createOrder.createOrder(foodMenus);
	}
}
