package com.gqshop.kiosk.core.usecase.staff_serving.get_order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.mockito.Mockito;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.entity.OrderStatus;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;

public class StaffServingGetOrderUsecaseTest {
	String fakeUserId = UUID.randomUUID().toString();
	FoodMenu fakeMenu = new FoodMenu(UUID.randomUUID(), RandomString.make(), RandomString.make(), RandomString.make());
	
	GetReceivedOrders getReceivedOrders = mock(GetReceivedOrders.class);
	GetFoodMenuWithOrderId getFoodMenuWithOrderId = mock(GetFoodMenuWithOrderId.class); 
	StaffServingGetOrderUsecase staffServingGetOrderUsecase = new StaffServingGetOrderUsecase(getReceivedOrders, getFoodMenuWithOrderId);
	

	@Test
	public void returnAllReceivedOrders() {
		Collection<Order> expected = givenSomeRecivedOrdersFound();
		Collection<Order> actual = staffServingGetOrderUsecase.getReceivedOrders();
		assertThat(actual.size()).isEqualTo(expected.size());
	}

	private Collection<Order> givenSomeRecivedOrdersFound() {
		Collection<Order> expected = new ArrayList<Order>();
		expected.add(new Order(UUID.fromString(fakeUserId),OrderStatus.ORDER_RECEIVED,1));
		expected.add(new Order(UUID.fromString(fakeUserId),OrderStatus.ORDER_RECEIVED,2));
		when(getReceivedOrders.getReceivedOrders()).thenReturn(expected);
		return expected;
	}
	
	@Test
	public void returnFoodMenuWithOrderId() {
		givenSomeRecivedOrdersFound();
		
		Collection<FoodMenu> expected = givenSomeRecivedOrdersFound(fakeUserId);
		Collection<FoodMenu> actual = staffServingGetOrderUsecase.getFoodMenuWithOrderId(fakeUserId);
		assertThat(expected.size()).isEqualTo(actual.size());
	}
	
	@Test
	public void errorWhenNoOrderWithOrderId() {
		givenNoRecivedOrdersFound(fakeUserId);		
		
		assertThatExceptionOfType(OrderNotFoundException.class)
		.isThrownBy(() -> staffServingGetOrderUsecase.getFoodMenuWithOrderId(fakeUserId));
	}

	
	@Test
	public void errorWhenNoFoodMenuWithOrderId() {
		givenSomeRecivedOrdersFound();
		givenNoRecivedFoodMenusFound(fakeUserId);

		assertThatExceptionOfType(FoodMenuNotFoundInOrderException.class)
		.isThrownBy(() -> staffServingGetOrderUsecase.getFoodMenuWithOrderId(fakeUserId));
	}

	
	private Collection<FoodMenu> givenSomeRecivedOrdersFound(String userid) {
		Collection<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
		foodMenus.add(fakeMenu);
		when(getFoodMenuWithOrderId.getFoodMenuWithOrderId(userid)).thenReturn(foodMenus);
		return foodMenus;
	}

	private void givenNoRecivedOrdersFound(String userid) {
		when(getFoodMenuWithOrderId.getFoodMenuWithOrderId(userid)).thenThrow(new OrderNotFoundException());		
	}
	

	private void givenNoRecivedFoodMenusFound(String userid) {
		when(getFoodMenuWithOrderId.getFoodMenuWithOrderId(userid)).thenThrow(new FoodMenuNotFoundInOrderException());
	}
}
