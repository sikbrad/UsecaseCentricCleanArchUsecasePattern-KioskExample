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

@Profile("integrationtest")
public class FoodMenuDatabaseJdbcDataProvider implements GetAllFoodMenu, GetFoodMenuWithId {
    private JdbcTemplate jdbcTemplate;

	public FoodMenuDatabaseJdbcDataProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public FoodMenu getWithId(UUID id) {
		
		FoodMenu foodMenu = jdbcTemplate.queryForObject("SELECT id,name,description FROM GQSHOP.FOOD_MENU WHERE id = ?", rowMapper, id.toString());
		return foodMenu;
	}
	
	//@ref rowmapper tutorial https://www.youtube.com/watch?v=XL8lp0cCVks
	private RowMapper<FoodMenu> rowMapper = (ResultSet rs, int rowNum) ->{
		
		UUID id = UUID.fromString(rs.getString(1));
		String name = rs.getString(2);
		String description = rs.getString(3);
		
		FoodMenu foodMenu = new FoodMenu(id, name, description);
		return foodMenu;
		
	};
	
	@Override
	public Collection<FoodMenu> getAll() {		
		return jdbcTemplate.query("SELECT id,name,description FROM GQSHOP.FOOD_MENU ", rowMapper);
	}

}
