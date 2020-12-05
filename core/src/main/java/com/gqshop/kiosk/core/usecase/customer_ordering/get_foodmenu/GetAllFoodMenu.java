package com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu;

import java.util.Collection;
import com.gqshop.kiosk.core.entity.FoodMenu;

public interface GetAllFoodMenu {	
    Collection<FoodMenu> getAll();
}
