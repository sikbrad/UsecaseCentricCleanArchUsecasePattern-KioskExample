package com.gqshop.kiosk.dataprovider.database.order;

import java.util.Collection;

import org.springframework.context.annotation.Profile;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.CreateOrder;

@Profile("default")
public class OrderDatabaseMockProvider implements CreateOrder{
	private Collection<Order> orderList;

	public OrderDatabaseMockProvider(Collection<Order> orderList) {
		this.orderList = orderList;
	}


	@Override
	public Order createOrder(Collection<FoodMenu> foodMenus) {
		Order order = new Order(foodMenus);
		
		orderList.add(order);
		
		return order;
	}
}
