package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import java.util.Collection;

import com.gqshop.kiosk.core.entity.Order;

public interface GetReceivedOrders {
    Collection<Order> getReceivedOrders();
}
