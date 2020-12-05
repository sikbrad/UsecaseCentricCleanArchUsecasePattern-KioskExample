package com.gqshop.kiosk.integration.database.customer_ordering;

import static org.mockito.Mockito.mock;

import org.springframework.test.web.servlet.MockMvc;

import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;

public class CustomerOrderingEntrypointRestIntegrationTest {
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase = mock(CustomerOrderingGetFoodMenuUsecase.class);

    private MockMvc mockMvc;
}
