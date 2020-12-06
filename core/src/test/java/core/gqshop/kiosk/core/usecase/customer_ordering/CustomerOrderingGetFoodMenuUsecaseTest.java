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
import com.gqshop.kiosk.core.usecase.customer_ordering.get_foodmenu.FoodMenuNotFoundException;

public class CustomerOrderingGetFoodMenuUsecaseTest {
	
	FoodMenu fakeMenu = new FoodMenu(UUID.randomUUID(), RandomString.make(), RandomString.make());
	UUID fakeUuid2 = UUID.randomUUID();

	GetAllFoodMenu getAllFoodMenu = mock(GetAllFoodMenu.class);
	GetFoodMenuWithId getFoodMenuWithId = mock(GetFoodMenuWithId.class);
	CustomerOrderingGetFoodMenuUsecase customerOrderingGetFoodMenuUsecase = new CustomerOrderingGetFoodMenuUsecase(getAllFoodMenu,
			getFoodMenuWithId);

	@Test
	public void returnAllFoodMenu() {
		Collection<FoodMenu> expected = givenSomeFoodMenuFound();
		Collection<FoodMenu> actual = customerOrderingGetFoodMenuUsecase.getAllFoodMenu();
//		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
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

//	@Test
//	public void errorWhenMultipleMenuIsFoundForOneName() throws Exception {
//		givenFoodMenuHaveMultipleMunoForOneName();
//		assertThatExceptionOfType(MultipleMenuFoundForSingleNameException.class)
//				.isThrownBy(() -> customerOrderingGetFoodMenuUsecase.getFoodMenuWithId(fakeMenu.getName()));
//	}
//	private void givenFoodMenuNotExist() {
//		when(getAllFoodMenu.getAllFoodMenu()).thenReturn(new ArrayList<FoodMenu>());
//	}

//	private Collection<FoodMenu> givenOneFoodMenuFound() {
//		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
//		expected.add(new FoodMenu(fakeMenu));
//		when(getAllFoodMenu.getAllFoodMenu()).thenReturn(expected);
//		return expected;
//	}

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
//		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
//		expected.add(new FoodMenu(fakeMenu));
//		var fistone = expected.iterator().next();
//		when(getFoodMenuWithId.getFoodMenuWithId(fakeMenu.getName())).thenReturn(expected);
//		return fistone;
		
//		when(getFoodMenuWithId.getFoodMenuWithId(fakeMenu.getName()))
//		when(getAllFoodMenu.getAll()).thenReturn(expected);
		when(getFoodMenuWithId.getWithId(fakeMenu.getId())).thenReturn(expected);
		
		return expected;
	}
	
	private void givenNoFoodMenuFound(){
		when(getFoodMenuWithId.getWithId(fakeUuid2)).thenThrow(new FoodMenuNotFoundException());
	}

//	private void givenFoodMenuHaveMultipleMunoForOneName() {
//		Collection<FoodMenu> expected = new ArrayList<FoodMenu>();
//		expected.add(new FoodMenu(fakeMenu));
//		expected.add(new FoodMenu(fakeMenu)); // dup
//		when(getFoodMenuWithId.getFoodMenuWithId(fakeMenu.getName())).thenReturn(expected);
//	}

}
