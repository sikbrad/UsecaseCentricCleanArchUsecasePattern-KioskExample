package com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu;

import java.util.Collection;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class CustomerOrderingGetFoodMenuUsecase {
	private final GetAllFoodMenu getAllFoodMenu;
	private final GetFoodMenuWithId getFoodMenuWithId;
	private final GetFoodMenuWithName getFoodMenuWithName;
	
	public CustomerOrderingGetFoodMenuUsecase(GetAllFoodMenu getAllFoodMenu, GetFoodMenuWithId getFoodMenuWithId, GetFoodMenuWithName getFoodMenuWithName) {
        this.getAllFoodMenu = getAllFoodMenu;
		this.getFoodMenuWithId = getFoodMenuWithId;
		this.getFoodMenuWithName = getFoodMenuWithName;
    }
	
	public Collection<FoodMenu> getAllFoodMenu() {
		Collection<FoodMenu> allFoodMenu = getAllFoodMenu.getAll();
				
		return allFoodMenu;		
	}
	
	public GetFoodMenuWithName getGetFoodMenuWithName() {
		return getFoodMenuWithName;
	}
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    public FoodMenu getFoodMenuWithName(String name) {
		FoodMenu foodMenu = getFoodMenuWithName.getWithName(name);
		if(foodMenu == null) {
			throw new FoodMenuNotFoundException();
		}
		return foodMenu;
    }

	public FoodMenu getFoodMenuWithId(String id) {
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		}catch (IllegalArgumentException ee) { //when non-uuid input
			throw new FoodMenuNotFoundException();
		}
		FoodMenu foodMenu = getFoodMenuWithId.getWithId(uuid);
		
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
