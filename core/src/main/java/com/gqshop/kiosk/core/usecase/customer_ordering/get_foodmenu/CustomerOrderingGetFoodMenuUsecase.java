package com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu;

import java.util.Collection;
import java.util.UUID;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class CustomerOrderingGetFoodMenuUsecase {
	private final GetAllFoodMenu getAllFoodMenu;
	private final GetFoodMenuWithId getFoodMenuWithId;
	
	public CustomerOrderingGetFoodMenuUsecase(GetAllFoodMenu getAllFoodMenu, GetFoodMenuWithId getFoodMenuWithId) {
        this.getAllFoodMenu = getAllFoodMenu;
		this.getFoodMenuWithId = getFoodMenuWithId;
    }
	
	public Collection<FoodMenu> getAllFoodMenu() {
		Collection<FoodMenu> allFoodMenu = getAllFoodMenu.getAll();
				
		return allFoodMenu;		
	}
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public FoodMenu getFoodMenuWithId(String id) {
		FoodMenu foodMenu = getFoodMenuWithId.getWithId(UUID.fromString(id));
		
		if(foodMenu == null) {
			throw new FoodMenuNotFoundException();
		}
		
		return foodMenu;
		
//		if(searchResult.size() == 0) {
//			throw new MenuNotFoundException();
//		}else if(searchResult.size() > 1) {
//			throw new MultipleMenuFoundForSingleNameException();
//		}
//		
//		return searchResult.iterator().next();//get first one
	}
}
