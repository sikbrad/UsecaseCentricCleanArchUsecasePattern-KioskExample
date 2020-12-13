package com.gqshop.kiosk.core.valueobject;

import java.util.UUID;

public class OrderHasFoodMenu {
	UUID idFoodMenu;	
	UUID idOrder;
	
	public UUID getIdFoodMenu() {
		return idFoodMenu;
	}
	public void setIdFoodMenu(UUID idFoodMenu) {
		this.idFoodMenu = idFoodMenu;
	}
	public UUID getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(UUID idOrder) {
		this.idOrder = idOrder;
	}
	
	public OrderHasFoodMenu(UUID idFoodMenu, UUID idOrder) {
		super();
		this.idFoodMenu = idFoodMenu;
		this.idOrder = idOrder;
	}
	
	
	
}

