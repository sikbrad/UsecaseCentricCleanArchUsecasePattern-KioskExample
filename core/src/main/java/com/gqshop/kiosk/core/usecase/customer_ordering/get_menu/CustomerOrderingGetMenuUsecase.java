package com.gqshop.kiosk.core.usecase.customer_ordering.get_menu;

import java.util.Collection;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class CustomerOrderingGetMenuUsecase {
	private final GetAllMenu getAllMenu;
	private final GetMenuDetail getMenuDetail;
	
	public CustomerOrderingGetMenuUsecase(GetAllMenu getAllMenu, GetMenuDetail getMenuDetail) {
        this.getAllMenu = getAllMenu;
		this.getMenuDetail = getMenuDetail;
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

	public FoodMenu getMenuDetail(String foodname) {
		Collection<FoodMenu> searchResult = getMenuDetail.searchWithFoodName(foodname);
		
		if(searchResult.size() == 0) {
			throw new MenuNotFoundException();
		}else if(searchResult.size() > 1) {
			throw new MultipleMenuFoundForSingleNameException();
		}
		
		return searchResult.iterator().next();//get first one
	}
}
