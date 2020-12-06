package com.gqshop.kiosk.dataprovider.database.foodmenu;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetAllFoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithId;

public class FoodMenuDatabaseJdbcDataProvider implements GetAllFoodMenu, GetFoodMenuWithId {
//	private Collection<FoodMenu> foodMenuList;
    private JdbcTemplate jdbcTemplate;

	public FoodMenuDatabaseJdbcDataProvider(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public FoodMenu getWithId(UUID id) {
		
//		jdbcTemplate.query("SELECT id,name,description FROM GQSHOP.FOOD_MENU WHERE id = ?", rowMapper, id.toString());
		FoodMenu foodMenu = jdbcTemplate.queryForObject("SELECT id,name,description FROM GQSHOP.FOOD_MENU WHERE id = ?", rowMapper, id.toString());
//		jdbcTemplate.query(psc, rse)
		
//		Collection<FoodMenu> searchResult = foodMenuList.stream().filter(foodmenu -> foodmenu.getId().equals(id)).collect(Collectors.toList());		
//		if(searchResult.size() ==0) return null;		
//		return searchResult.iterator().next();
		return foodMenu;
	}

//	private class FoodMenuH2DatabaseDto{
//		String ID;
//		String NAME;
//		String DESCRIPTION;
//		int IDX;
//		java.sql.Timestamp CREATED_AT;  
//	}
	
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
//		return foodMenuList;
		
		return jdbcTemplate.query("SELECT id,name,description FROM GQSHOP.FOOD_MENU ", rowMapper);
	}

//	@Override
//	public Collection<FoodMenu> getAll() {
//		return foodMenuList;
//	}
//
//	@Override
//	public Collection<FoodMenu> searchWithId(String foodname) {
//		Collection<FoodMenu> searchResult = foodMenuList.stream().filter(foodmenu -> foodmenu.getName().equals(foodname)).collect(Collectors.toList());
//		return searchResult;
//	}

}
