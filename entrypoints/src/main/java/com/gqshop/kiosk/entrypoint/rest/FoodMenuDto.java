package com.gqshop.kiosk.entrypoint.rest;

import java.util.ArrayList;
import java.util.Collection;

import com.gqshop.kiosk.core.entity.FoodMenu;

public class FoodMenuDto extends ArrayList<FoodMenu>{

	public FoodMenuDto(Collection<FoodMenu> allFoodMenu) {
		// TODO Auto-generated constructor stub
		this.stream().forEach(x -> {
			this.add(x);
		});
	}

}
