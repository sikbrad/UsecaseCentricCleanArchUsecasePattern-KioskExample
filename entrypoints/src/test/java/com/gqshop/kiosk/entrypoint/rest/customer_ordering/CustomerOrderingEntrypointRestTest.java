package com.gqshop.kiosk.entrypoint.rest.customer_ordering;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.mockito.Mockito;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.entity.OrderStatus;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.CustomerOrderingCreateOrderUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;

public class CustomerOrderingEntrypointRestTest {

	FoodMenu fakeMenu1 = new FoodMenu("kimchi", "korean spicy dish", null);
	
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase = mock(CustomerOrderingGetFoodMenuUsecase.class);
	CustomerOrderingCreateOrderUsecase customerOrderingCreateOrderUsecase =  mock(CustomerOrderingCreateOrderUsecase.class);
    CustomerOrderingEntrypointRest customerOrderingEntrypointRest = new CustomerOrderingEntrypointRest(customerOrderingGetFoodMenuUsecase, customerOrderingCreateOrderUsecase); 
	
	@Test
	public void returnsAllFoodMenu() throws Exception{
		givenOneMenuExists();		
		Collection<FoodMenuDto> foodMenuList = customerOrderingEntrypointRest.getFoodMenuList();		
		assertThat(foodMenuList.size()).isEqualTo(1);		
	}
	
	@Test
	public void returnsFoodMenu() throws Exception{
		givenOneMenuExists();
		FoodMenuDto foodMenuDto = customerOrderingEntrypointRest.getFoodMenu(fakeMenu1.getName());
		
		assertThat(foodMenuDto.getId()).isEqualTo(fakeMenu1.getId().toString());
		assertThat(foodMenuDto.getName()).isEqualTo(fakeMenu1.getName());
		assertThat(foodMenuDto.getDescription()).isEqualTo(fakeMenu1.getDescription());		
	}
		
	@Test
	public void returnNullWhenNoFoodMenuFound() throws Exception{
		givenOneMenuExists();
		assertThat(customerOrderingEntrypointRest.getFoodMenu("not a food")).isEqualTo(null);
	}
	
	private void givenOneMenuExists() {
		Collection<FoodMenu> foodMenuList = new ArrayList<FoodMenu>();
		foodMenuList.add(fakeMenu1);
		when(customerOrderingGetFoodMenuUsecase.getAllFoodMenu()).thenReturn(foodMenuList);		
		when(customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(fakeMenu1.getName())).thenReturn(fakeMenu1);
		when(customerOrderingGetFoodMenuUsecase.getFoodMenuWithId("not a food")).thenThrow(new FoodMenuNotFoundException());
	}
	
	@Test
	public void canCreateOrder() throws Exception{		
		givenOneMenuExists();
		OrderDto expected = givenCanCreateOrder(fakeMenu1);
		OrderDto actual = customerOrderingEntrypointRest.createOrder(fakeMenu1.getId().toString(), 3);
		
		assertThat(expected.getFoodMenus().length).isEqualTo(actual.getFoodMenus().length);
		assertThat(expected.getOrderNumber()).isEqualTo(actual.getOrderNumber());
		
	}

	private OrderDto givenCanCreateOrder(FoodMenu foodMenu) {
		Collection<FoodMenu> orderingFoodMenus = new ArrayList<FoodMenu>();
		orderingFoodMenus.add(foodMenu);
		orderingFoodMenus.add(foodMenu);
		orderingFoodMenus.add(foodMenu);		
		Order order = new Order(orderingFoodMenus);
		OrderDto orderDto = new OrderDto(order);
		when(customerOrderingCreateOrderUsecase.createOrder(Mockito.anyCollection())).thenReturn(order);
		return orderDto;
	}

}