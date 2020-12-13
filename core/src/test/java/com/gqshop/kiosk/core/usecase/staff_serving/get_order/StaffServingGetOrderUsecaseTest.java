package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import static org.mockito.Mockito.mock;

public class StaffServingGetOrderUsecaseTest {

//	GetAllFoodMenu getAllFoodMenu = mock(GetAllFoodMenu.class);
//	GetFoodMenuWithId getFoodMenuWithId = mock(GetFoodMenuWithId.class);
//	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase = new CustomerOrderingGetFoodMenuUsecase(getAllFoodMenu,
//			getFoodMenuWithId);
	
	GetReceivedOrders getReceivedOrders = mock(GetReceivedOrders.class);
	GetFoodMenuWithOrderId getFoodMenuOfOrder = mock(GetFoodMenuWithOrderId.class); 
	StaffServingGetOrderUsecase staffServingGetOrderUsecase = new StaffServingGetOrderUsecase(getReceivedOrders, getFoodMenuOfOrder);
}
