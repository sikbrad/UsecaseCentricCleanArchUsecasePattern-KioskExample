package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetAllFoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithId;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithName;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase(GetAllFoodMenu getAllFoodMenu, GetFoodMenuWithId getFoodMenuWithId, GetFoodMenuWithName getFoodMenuWithName) {
        return new CustomerOrderingGetFoodMenuUsecase(getAllFoodMenu,getFoodMenuWithId, getFoodMenuWithName);
    }
}
