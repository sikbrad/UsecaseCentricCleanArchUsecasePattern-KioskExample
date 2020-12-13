package com.gqshop.kiosk.entrypoint.rest.customer_ordering;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;
import com.gqshop.kiosk.entrypoint.rest.customer_ordering.CustomerOrderingEntrypointRest;
import com.gqshop.kiosk.entrypoint.rest.customer_ordering.FoodMenuDto;

public class CustomerOrderingEntrypointRestTest {

	FoodMenu fakeMenu1 = new FoodMenu("kimchi", "korean spicy dish", null);
	
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase = mock(CustomerOrderingGetFoodMenuUsecase.class);	
    CustomerOrderingEntrypointRest customerOrderingEntrypointRest = new CustomerOrderingEntrypointRest(customerOrderingGetFoodMenuUsecase); 
	
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

}