package com.gqshop.kiosk.core.usecase.customer_ordering.get_menu;

import java.util.Collection;
import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetAllMenu {	
    Collection<FoodMenu> getAllFoodMenu();
}
