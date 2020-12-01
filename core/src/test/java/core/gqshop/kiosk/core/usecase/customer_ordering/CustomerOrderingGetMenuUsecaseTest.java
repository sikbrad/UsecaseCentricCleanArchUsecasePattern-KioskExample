package core.gqshop.kiosk.core.usecase.customer_ordering;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.mockito.Mockito;

import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.CustomerOrderingGetMenuUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.GetAllMenu;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.GetMenuDetail;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.MenuNotExistExcepion;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.MenuNotFoundException;
import com.gqshop.kiosk.core.usecase.customer_ordering.get_menu.MultipleMenuFoundForSingleNameException;

public class CustomerOrderingGetMenuUsecaseTest {

	GetAllMenu getAllMenu = mock(GetAllMenu.class);
	GetMenuDetail getMenuDetail = mock(GetMenuDetail.class);
	CustomerOrderingGetMenuUsecase customerOrderingGetMenuUsecase = new CustomerOrderingGetMenuUsecase(getAllMenu,
			getMenuDetail);

	@Test
	public void returnAllMenu() {
		var expected = givenFoodMenuExist();
		var actual = customerOrderingGetMenuUsecase.getAllMenu();
		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
	}

	@Test
	public void errorWhenFoodMenuNotExist() {
		givenFoodMenuNotExist();
		assertThatExceptionOfType(MenuNotExistExcepion.class)
				.isThrownBy(() -> customerOrderingGetMenuUsecase.getAllMenu());
	}

	@Test
	public void returnsFoodMenuDetail() {
		var expected = givenFoodMenuFound();
		var actual = customerOrderingGetMenuUsecase.getMenuDetail("kimchi");
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void errorWhenMenuIsNotFound() throws Exception {
		givenFoodMenuNotExist();
		assertThatExceptionOfType(MenuNotFoundException.class)
				.isThrownBy(() -> customerOrderingGetMenuUsecase.getMenuDetail("spatetti"));
	}

	@Test
	public void errorWhenMultipleMenuIsFoundForOneName() throws Exception {
		givenFoodMenuHaveMultipleMunoForOneName();
		assertThatExceptionOfType(MultipleMenuFoundForSingleNameException.class)
				.isThrownBy(() -> customerOrderingGetMenuUsecase.getMenuDetail("hamburger"));
	}


	private void givenFoodMenuNotExist() {
		when(getAllMenu.getAllFoodMenu()).thenReturn(new ArrayList<FoodMenu>());
	}

	private Collection<FoodMenu> givenFoodMenuExist() {
		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
		expected.add(new FoodMenu("spagetti", "italian noodle"));
		when(getAllMenu.getAllFoodMenu()).thenReturn(expected);
		return expected;
	}

	private FoodMenu givenFoodMenuFound() {
		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
		expected.add(new FoodMenu("kimchi", "korean spicy something"));
		var fistone = expected.iterator().next();
		when(getMenuDetail.searchWithFoodName("kimchi")).thenReturn(expected);
		return fistone;
	}

	private void givenFoodMenuHaveMultipleMunoForOneName() {
		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
		expected.add(new FoodMenu("hamburger", ""));
		expected.add(new FoodMenu("hamburger", "")); //dup
		when(getMenuDetail.searchWithFoodName("hamburger")).thenReturn(expected);		
	}

}
