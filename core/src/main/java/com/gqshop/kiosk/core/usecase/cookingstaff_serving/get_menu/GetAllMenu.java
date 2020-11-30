package com.gqshop.kiosk.core.usecase.cookingstaff_serving.get_menu;

import java.util.Collection;
import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetAllMenu {	
    Collection<FoodMenu> getAllFoodMenu();
}
