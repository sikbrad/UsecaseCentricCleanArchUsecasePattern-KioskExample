package com.gqshop.kiosk.entrypoint.rest.customer_ordering;

import java.util.UUID;

/**
 * rest response model
 * @author USER
 *
 */
public class FoodMenuDto {
	private final String id;
	private final String name;
	private final String description;
	
	public FoodMenuDto(UUID id, String name, String description) {
		super();
		this.id = id.toString();
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
}
