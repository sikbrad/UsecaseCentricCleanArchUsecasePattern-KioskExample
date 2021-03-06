package com.gqshop.kiosk.entrypoint.rest.customer_ordering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.CustomerOrderingCreateOrderUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner {

	@Autowired
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase;
	
	@Autowired
	CustomerOrderingCreateOrderUsecase customerOrderingCreateOrderUsecase;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public CustomerOrderingEntrypointRest(CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase, CustomerOrderingCreateOrderUsecase customerOrderingCreateOrderUsecase) {
		this.customerOrderingGetFoodMenuUsecase = customerOrderingGetFoodMenuUsecase;
		this.customerOrderingCreateOrderUsecase = customerOrderingCreateOrderUsecase;
	}

	@Autowired
	Environment env;
	
	@GetMapping
	public String home() {
		JSONObject jo = new JSONObject();
		
		jo.put("name", "gqshop APIs");
		jo.put("version", "1.0.0");		
		String currProfiles = String.join(";", env.getActiveProfiles());
		if(currProfiles.length() == 0) {
			currProfiles = "(profile undecided - it is set as default)";
		}				
		jo.put("profile", currProfiles);
		
		
		logger.debug(String.format("/api returns : %s", jo.toString()));
		logger.info(String.format("/api returns : %s", jo.toString()));
		
		return jo.toString();
	}

	@GetMapping(value = "/foodmenu")
	public Collection<FoodMenuDto> getFoodMenuList() {
		Collection<FoodMenu> allFoodMenu = customerOrderingGetFoodMenuUsecase.getAllFoodMenu();
		return toDto(allFoodMenu);
	}

	@GetMapping(value = "/foodmenu/uuid/{id}")
	public FoodMenuDto getFoodMenu(@PathVariable(value = "id") String id) {
		try {
			return toFoodMenuDto(customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(id));
		} catch (FoodMenuNotFoundException ee) {
			return null;
		}
	}
	
	@GetMapping(value = "/foodmenu/{foodname}")
	public FoodMenuDto getFoodMenuWithName(@PathVariable(value = "foodname") String foodname) {
		try {
			return toFoodMenuDto(customerOrderingGetFoodMenuUsecase.getFoodMenuWithName(foodname));
		} catch (FoodMenuNotFoundException ee) {
			return null;
		}
	}
	
	@PostMapping(value = "/order")
	public OrderDto createOrder(@PathParam(value ="foodid") String foodid, @PathParam(value="qty") int qty) {
		ArrayList<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
		FoodMenu foodMenu = customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(foodid);
		for(int idx=0;idx<qty;idx++) {
			foodMenus.add(foodMenu);	
		}		
		return toOrderDto(customerOrderingCreateOrderUsecase.createOrder(foodMenus));		
	}

	private Collection<FoodMenuDto> toDto(Collection<FoodMenu> allFoodMenu) {
		return allFoodMenu.stream().map(x -> toFoodMenuDto(x)).collect(Collectors.toList());
	}

	private FoodMenuDto toFoodMenuDto(FoodMenu foodMenu) {
		return new FoodMenuDto(foodMenu.getId(), foodMenu.getName(), foodMenu.getDescription(), foodMenu.getImageUrl());
	}

	private OrderDto toOrderDto(Order order) {
		return new OrderDto(order);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingEntrypointRest bean created");
	}

}
