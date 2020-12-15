package com.gqshop.kiosk.dataprovider.database.order;

import java.util.Collection;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.CreateOrder;

@Profile("integrationtest")
public class OrderDatabaseJdbcProvider implements CreateOrder {
	private JdbcTemplate jdbcTemplate;

	public OrderDatabaseJdbcProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Order createOrder(Collection<FoodMenu> foodMenus) {
		// add order
//		jdbcTemplate.query(psc, rse)
		// add order has food menu
//		jdbcTemplate.query(psc, rse)
		return null;
	}

}
