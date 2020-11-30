package com.gqshop.kiosk.core.entity;

import java.util.UUID;

public class FoodMenu {
	private UUID id;
	private String name;
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UUID getId() {
		return id;
	}
	public FoodMenu(UUID id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public FoodMenu(String name, String description) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.description = description;
	}
	
	
}
