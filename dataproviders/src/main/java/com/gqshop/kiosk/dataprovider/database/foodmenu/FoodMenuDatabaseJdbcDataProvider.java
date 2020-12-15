package com.gqshop.kiosk.dataprovider.database.foodmenu;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetAllFoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithId;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithName;

@Profile("integrationtest")
public class FoodMenuDatabaseJdbcDataProvider implements GetAllFoodMenu, GetFoodMenuWithId, GetFoodMenuWithName {
	private JdbcTemplate jdbcTemplate;

	public FoodMenuDatabaseJdbcDataProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public FoodMenu getWithId(UUID id) {

		FoodMenu foodMenu = jdbcTemplate.queryForObject(
				"SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU WHERE id = ?;", rowMapper, id.toString());
		return foodMenu;
	}

	// @ref rowmapper tutorial https://www.youtube.com/watch?v=XL8lp0cCVks
	private RowMapper<FoodMenu> rowMapper = (ResultSet rs, int rowNum) -> {

		UUID id = UUID.fromString(rs.getString(1));
		String name = rs.getString(2);
		String description = rs.getString(3);
		String imageUrl = rs.getString(4);

		FoodMenu foodMenu = new FoodMenu(id, name, description, imageUrl);
		return foodMenu;

	};

	@Override
	public Collection<FoodMenu> getAll() {
		return jdbcTemplate.query("SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU;", rowMapper);
	}

	@Override
	public FoodMenu getWithName(String name) {
		FoodMenu foodMenu = jdbcTemplate.queryForObject(String.format("SELECT id,name,description,image_url FROM GQSHOP.FOOD_MENU WHERE name = '%s';",name), rowMapper);
		return foodMenu;
	}


}
