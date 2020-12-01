package com.gqshop.kiosk.core.usecase.customer_ordering.get_menu;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class CustomerOrderingGetMenuUsecase {
	private final GetAllMenu getAllMenu;
	
	public CustomerOrderingGetMenuUsecase(GetAllMenu getAllMenu) {
        this.getAllMenu = getAllMenu;
    }
	
	public Collection<FoodMenu> getAllMenu() {
		Collection<FoodMenu> allMenu = getAllMenu.getAllFoodMenu();
		
		if(allMenu.size() == 0) {
			throw new MenuNotExistExcepion();
		}
		
		return allMenu;		
	}
	

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
