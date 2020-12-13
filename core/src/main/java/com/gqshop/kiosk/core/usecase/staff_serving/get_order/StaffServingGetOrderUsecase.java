package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.valueobject.OrderHasFoodMenu;

public class StaffServingGetOrderUsecase {
	private final GetReceivedOrders getReceivedOrders;
	private final GetFoodMenuWithOrderId getFoodMenuOfOrder;
	private final GetOrderHasFoodMenuWithOrderId getOrderHasFoodMenuWithOrderId;
	private final GetFoodMenuWithId getFoodMenuWithId; 

	public GetFoodMenuWithId getGetFoodMenuWithId() {
		return getFoodMenuWithId;
	}

	public GetOrderHasFoodMenuWithOrderId getGetOrderHasFoodMenuWithOrderId() {
		return getOrderHasFoodMenuWithOrderId;
	}

	public GetFoodMenuWithOrderId getGetFoodMenuOfOrder() {
		return getFoodMenuOfOrder;
	}

	public GetReceivedOrders getGetReceivedOrders() {
		return getReceivedOrders;
	}

	public StaffServingGetOrderUsecase(GetReceivedOrders getReceivedOrders, GetFoodMenuWithOrderId getFoodMenuOfOrder, GetOrderHasFoodMenuWithOrderId getOrderHasFoodMenuWithOrderId, GetFoodMenuWithId getFoodMenuWithId) {
		super();
		this.getReceivedOrders = getReceivedOrders;
		this.getFoodMenuOfOrder = getFoodMenuOfOrder;
		this.getOrderHasFoodMenuWithOrderId = getOrderHasFoodMenuWithOrderId;
		this.getFoodMenuWithId = getFoodMenuWithId;
	}
	
	public Collection<FoodMenu> getFoodMenuWithOrderId(String orderId) {
		Collection<Order> receivedOrders = getReceivedOrders.getReceivedOrders();
		
		Iterator<Order> iterator = receivedOrders.iterator();
		Order order = null;
	    while (iterator.hasNext()) {
	        Order examinee = iterator.next();
	        if (examinee.getId().equals(orderId)) {
	            order = examinee;
	            break;
	        }
	    }
		
		if(order == null) {
			throw new OrderNotFoundException();
		}
		
		//relation
		Collection<OrderHasFoodMenu> orderHasFoodMenuList = getOrderHasFoodMenuWithOrderId.getOrderHasFoodMenuWithOrderId(orderId);
		
		if(orderHasFoodMenuList.size() == 0) {
			throw new FoodMenuNotFoundInOrderException();
		}
		
		Collection<FoodMenu> foodMenuOfOrder = new ArrayList<FoodMenu>();
		Iterator<OrderHasFoodMenu> iterator2 = orderHasFoodMenuList.iterator();
	    while (iterator2.hasNext()) {
	    	OrderHasFoodMenu examinee = iterator2.next();	    	
	    	foodMenuOfOrder.add(getFoodMenuWithId.getWithId(examinee.getIdFoodMenu().toString()));	    	
	    }
	    
	    return foodMenuOfOrder;
	}
	
	public Collection<Order> getReceivedOrders(){
		//get orders that not started cooking
		Collection<Order> receivedOrders = getReceivedOrders.getReceivedOrders();
		return receivedOrders;
	}
	
	
}
