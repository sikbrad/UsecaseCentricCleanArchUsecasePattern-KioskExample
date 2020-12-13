package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import java.util.Collection;
import java.util.Iterator;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;

public class StaffServingGetOrderUsecase {
	private final GetReceivedOrders getReceivedOrders;
	private final GetFoodMenuWithOrderId getFoodMenuWithOrderId;

	private GetFoodMenuWithOrderId getFoodMenuWithOrderId() {
		return getFoodMenuWithOrderId;
	}

	private GetReceivedOrders getGetReceivedOrders() {
		return getReceivedOrders;
	}

	public StaffServingGetOrderUsecase(GetReceivedOrders getReceivedOrders, GetFoodMenuWithOrderId getFoodMenuOfOrder) {
		super();
		this.getReceivedOrders = getReceivedOrders;
		this.getFoodMenuWithOrderId = getFoodMenuOfOrder;
	}

	public Collection<FoodMenu> getFoodMenuWithOrderId(String orderId) {

		// test for oder existence
		Collection<Order> receivedOrders = getReceivedOrders.getReceivedOrders();
		Iterator<Order> iterator = receivedOrders.iterator();
		Order order = null;
		while (iterator.hasNext()) {
			Order examinee = iterator.next();
			if (examinee.getId().toString().equals(orderId)) {
				order = examinee;
				break;
			}
		}

		if (order == null) {
			throw new OrderNotFoundException();
		}

		// getting food menus in order
		Collection<FoodMenu> foodmenusOfOrder = getFoodMenuWithOrderId.getFoodMenuWithOrderId(orderId);

		if (foodmenusOfOrder.size() == 0) {
			throw new FoodMenuNotFoundInOrderException();
		}

		return foodmenusOfOrder;
	}

	public Collection<Order> getReceivedOrders() {
		// get orders that not started cooking
		Collection<Order> receivedOrders = getReceivedOrders.getReceivedOrders();
		return receivedOrders;
	}

}
