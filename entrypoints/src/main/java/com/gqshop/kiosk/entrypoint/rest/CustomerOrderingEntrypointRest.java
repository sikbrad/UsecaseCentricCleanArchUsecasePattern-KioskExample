package com.gqshop.kiosk.entrypoint.rest;

import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.cookingstaff_serving.get_menu.CustomerOrderingGetMenuUsecase;
import com.gqshop.kiosk.core.usecase.cookingstaff_serving.get_menu.MenuNotExistExcepion;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner{
	
	CustomerOrderingGetMenuUsecase customerOrderingGetMenuUsecase;

    public CustomerOrderingEntrypointRest(CustomerOrderingGetMenuUsecase customerOrderingGetMenuUsecase) {
        this.customerOrderingGetMenuUsecase = customerOrderingGetMenuUsecase;
    }
		
	@GetMapping(value="/")
	public String home() {
		return "API index called";
	}
	
	@GetMapping(value="/foodmenu")
	public FoodMenuArrayDto getFoodMenuList() {
		try {
			Collection<FoodMenu> allFoodMenu = customerOrderingGetMenuUsecase.getAllMenu();
            return toDto(allFoodMenu);
        } catch (MenuNotExistExcepion e) {
            System.out.println("no foodmenu exists");
//            throw new NotFoundException();
//            return "{count:0}";
        }
		
//		return "API index called";
		return null;
	}

	private FoodMenuArrayDto toDto(Collection<FoodMenu> allFoodMenu) {
		FoodMenuArrayDto foodMenuDto = new FoodMenuArrayDto(allFoodMenu);
		return foodMenuDto;
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("CustomerOrderingEntrypointRest bean created");
	}

	
	
}
