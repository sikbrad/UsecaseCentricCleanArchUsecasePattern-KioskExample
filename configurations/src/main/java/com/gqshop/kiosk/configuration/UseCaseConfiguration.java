package com.gqshop.kiosk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.CustomerOrderingGetMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.GetAllMenu;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CustomerOrderingGetMenuUsecase customerOrderingGetMenuUsecase(GetAllMenu getAllMenu) {
        return new CustomerOrderingGetMenuUsecase(getAllMenu);
    }
}
