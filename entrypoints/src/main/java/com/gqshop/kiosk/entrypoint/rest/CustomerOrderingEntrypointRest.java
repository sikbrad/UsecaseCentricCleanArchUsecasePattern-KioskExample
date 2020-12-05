package com.gqshop.kiosk.entrypoint.rest;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.CustomerOrderingGetMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.MenuNotExistExcepion;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.MenuNotFoundException;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner{
	
	@Autowired
	CustomerOrderingGetMenuUsecase customerOrderingGetMenuUsecase;
	
	public CustomerOrderingEntrypointRest(CustomerOrderingGetMenuUsecase customerOrderingGetMenuUsecase) {
		this.customerOrderingGetMenuUsecase = customerOrderingGetMenuUsecase;
	}
		
	@GetMapping
	public String home() {
		return "API index called";
	}
	
	@GetMapping(value="/foodmenu")
	public Collection<FoodMenuDto> getFoodMenuList(){
		try {
			Collection<FoodMenu> allFoodMenu = customerOrderingGetMenuUsecase.getAllMenu();
            return toDto(allFoodMenu);
        } catch (MenuNotExistExcepion e) {
//            System.out.println("no foodmenu exists");
//            throw new MenuNotExistExcepion();
//            return "{count:0}";
        	return null;
        }
		
//		return "API index called";
//		return null;
	}
	
	@GetMapping(value="/foodmenu/{foodname}")
	public FoodMenuDto getFoodMenu(@PathVariable(value="foodname") String foodname) {
		try {
			return toDto(customerOrderingGetMenuUsecase.getMenuDetail(foodname));	
		}catch(MenuNotFoundException ee) {
			return null;
		}		
	}

//	private FoodMenuArrayDto toDto(Collection<FoodMenu> allFoodMenu) {
//		FoodMenuArrayDto foodMenuDto = new FoodMenuArrayDto(allFoodMenu);
//		return foodMenuDto;
//	}
	
	private Collection<FoodMenuDto> toDto(Collection<FoodMenu> allFoodMenu) {
		return allFoodMenu.stream().map(x->toDto(x)).collect(Collectors.toList());
	}
	
	private FoodMenuDto toDto(FoodMenu foodMenu) {
		return new FoodMenuDto(foodMenu.getId(),foodMenu.getName(),foodMenu.getDescription());
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("CustomerOrderingEntrypointRest bean created");
	}

	
	
}
