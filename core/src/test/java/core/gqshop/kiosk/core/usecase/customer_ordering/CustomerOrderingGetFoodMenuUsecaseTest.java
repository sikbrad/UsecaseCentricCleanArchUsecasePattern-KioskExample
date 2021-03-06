package core.gqshop.kiosk.core.usecase.customer_ordering;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.CustomerOrderingGetFoodMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetAllFoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithId;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.GetFoodMenuWithName;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;

public class CustomerOrderingGetFoodMenuUsecaseTest {
	
	FoodMenu fakeMenu = new FoodMenu(UUID.randomUUID(), RandomString.make(), RandomString.make(), RandomString.make());
	UUID fakeUuid2 = UUID.randomUUID();

	GetAllFoodMenu getAllFoodMenu = mock(GetAllFoodMenu.class);
	GetFoodMenuWithId getFoodMenuWithId = mock(GetFoodMenuWithId.class);
	GetFoodMenuWithName getFoodMenuWithName = mock(GetFoodMenuWithName.class);
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase = new CustomerOrderingGetFoodMenuUsecase(getAllFoodMenu,
			getFoodMenuWithId, getFoodMenuWithName );

	@Test
	public void returnAllFoodMenu() {
		Collection<FoodMenu> expected = givenSomeFoodMenuFound();
		Collection<FoodMenu> actual = customerOrderingGetFoodMenuUsecase.getAllFoodMenu();
		assertThat(actual.size()).isEqualTo(expected.size());
	}

	@Test
	public void returnsFoodMenuWithId() {
		FoodMenu expected = givenOneFoodMenuFound();
		FoodMenu actual = customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(fakeMenu.getId().toString());
		assertThat(actual.getId()).isEqualTo(expected.getId());
	}

	@Test
	public void errorWhenMenuIsNotFound() throws Exception {
		givenNoFoodMenuFound();
		assertThatExceptionOfType(FoodMenuNotFoundException.class)
				.isThrownBy(() -> customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(fakeUuid2.toString()));
	}
	

	@Test
	public void returnsFoodMenuWithName() {
		String foodname = fakeMenu.getName();
		FoodMenu expected = givenFoodMenuFoundWithName(foodname);
		FoodMenu actual = customerOrderingGetFoodMenuUsecase.getFoodMenuWithName(foodname);
		assertThat(actual.getName()).isEqualTo(expected.getName());
	}

	private FoodMenu givenFoodMenuFoundWithName(String foodname) {
		FoodMenu foodMenu = new FoodMenu(foodname, "", "");
		when(getFoodMenuWithName.getWithName(foodname)).thenReturn(foodMenu);
		return foodMenu;
	}

	@Test
	public void errorWhenMenuIsNotFoundWithName() throws Exception {
		String foodname = fakeMenu.getName();
		givenNoFoodMenuFoundWithName(foodname);
		assertThatExceptionOfType(FoodMenuNotFoundException.class)
				.isThrownBy(() -> customerOrderingGetFoodMenuUsecase.getFoodMenuWithName(foodname));
	}

	private void givenNoFoodMenuFoundWithName(String foodname) {
		when(getFoodMenuWithName.getWithName(foodname)).thenThrow(new FoodMenuNotFoundException());		
	}

	private Collection<FoodMenu> givenSomeFoodMenuFound() {
		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
		expected.add(new FoodMenu(fakeMenu));
		expected.add(new FoodMenu(fakeMenu));
		expected.add(new FoodMenu(fakeMenu));
		when(getAllFoodMenu.getAll()).thenReturn(expected);
		
		return expected;
	}
	
	private FoodMenu givenOneFoodMenuFound() {
		FoodMenu expected = new FoodMenu(fakeMenu);
		when(getFoodMenuWithId.getWithId(fakeMenu.getId())).thenReturn(expected);
		
		return expected;
	}
	
	private void givenNoFoodMenuFound(){
		when(getFoodMenuWithId.getWithId(fakeUuid2)).thenThrow(new FoodMenuNotFoundException());
	}

}
