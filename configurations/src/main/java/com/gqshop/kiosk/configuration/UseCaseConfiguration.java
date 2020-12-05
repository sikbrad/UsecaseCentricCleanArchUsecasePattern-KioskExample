package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetAllFoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithId;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase(GetAllFoodMenu getAllFoodMenu, GetFoodMenuWithId getFoodMenuWithId) {
        return new CustomerOrderingGetFoodMenuUsecase(getAllFoodMenu,getFoodMenuWithId);
    }
}
