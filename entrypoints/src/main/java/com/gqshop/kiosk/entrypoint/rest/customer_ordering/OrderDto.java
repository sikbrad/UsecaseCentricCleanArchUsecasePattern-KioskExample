package com.gqshop.kiosk.entrypoint.rest.customer_ordering;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;

public class OrderDto {
	private final String id;
	private final String orderStatus;

	private final int orderNumber; 
	private final FoodMenu[] foodMenus;

	public OrderDto(String id, String orderStatus, int orderNumber, FoodMenu[] foodMenus) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.orderNumber = orderNumber;
		this.foodMenus = foodMenus;
	}
	

	public OrderDto(Order order) {
		this.id = order.getId().toString();
		this.orderStatus = order.getOrderStatus().toString();
		this.orderNumber = order.getOrderNumber();
		this.foodMenus = order.getFoodMenus().toArray(new FoodMenu[order.getFoodMenus().size()]);
	}


	public String getId() {
		return id;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public FoodMenu[] getFoodMenus() {
		return foodMenus;
	}
}
