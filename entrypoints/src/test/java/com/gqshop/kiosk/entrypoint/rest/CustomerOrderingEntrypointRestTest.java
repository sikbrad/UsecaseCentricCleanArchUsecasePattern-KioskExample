package com.gqshop.kiosk.entrypoint.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;

public class CustomerOrderingEntrypointRestTest {

	FoodMenu fakeMenu1 = new FoodMenu("kimchi", "korean spicy dish");
//	FoodMenu fakeMenu2 = new FoodMenu("tongdak", "korean chicken dish");
	
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
	
//	@Test
//	public void errorWhenNoFoodMenuExist() throws Exception{
//		givenFoodMenuNotExist();
//		assertThatExceptionOfType(MenuNotExistExcepion.class).isThrownBy(
//				() -> customerOrderingEntrypointRest.getFoodMenuList()
//		);
//	}
//	@Test
//	public void returnNullWhenNoFoodMenuExist() throws Exception{
//		givenFoodMenuNotExist();
//		assertThat(customerOrderingEntrypointRest.getFoodMenuList()).isEqualTo(null);
//	}
	
	@Test
	public void returnNullWhenNoFoodMenuFound() throws Exception{
		givenOneMenuExists();
		assertThat(customerOrderingEntrypointRest.getFoodMenu("not a food")).isEqualTo(null);
	}
	
//	@Test
//	public void errorWhenNoFoodMenuFound() throws Exception{
//		givenFoodMenuNotExist();
//		assertThatExceptionOfType(MenuNotFoundException.class).isThrownBy(
//				() -> customerOrderingEntrypointRest.getFoodMenu("not a food")
//		);
//	}

//	private void givenFoodMenuNotExist() {
//		when(customerOrderingGetFoodMenuUsecase.getAllFoodMenu()).thenThrow(new MenuNotExistExcepion());
//	}

	private void givenOneMenuExists() {
		Collection<FoodMenu> foodMenuList = new ArrayList<FoodMenu>();
		foodMenuList.add(fakeMenu1);
		when(customerOrderingGetFoodMenuUsecase.getAllFoodMenu()).thenReturn(foodMenuList);		
		when(customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(fakeMenu1.getName())).thenReturn(fakeMenu1);
		when(customerOrderingGetFoodMenuUsecase.getFoodMenuWithId("not a food")).thenThrow(new FoodMenuNotFoundException());
	}

}