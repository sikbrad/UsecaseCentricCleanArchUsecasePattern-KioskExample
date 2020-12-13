package com.gqshop.kiosk.core.entity;

import java.util.UUID;

public class Order {
	private UUID id;
	private OrderStatus orderStatus;
	private int orderNumber; 
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Order(UUID id, OrderStatus orderStatus, int orderNumber) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.orderNumber = orderNumber;
	}
	public Order(OrderStatus orderStatus, int orderNumber) {
		super();
		this.id = UUID.randomUUID();
		this.orderStatus = orderStatus;
		this.orderNumber = orderNumber;
	}
		
	
}
