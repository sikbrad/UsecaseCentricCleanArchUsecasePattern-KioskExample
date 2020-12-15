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
import com.gqshop.kiosk.core.entity.Order;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.CreateOrder;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.CustomerOrderingCreateOrderUsecase;
import com.gqshop.kiosk.core.usecase.customer_ordering.create_order.NoFoodMenuInNewOrderException;
import com.gqshop.kiosk.core.usecase.staff_serving.get_order.FoodMenuNotFoundInOrderException;

public class CustomerOrderingCreateOrderUsecaseTest {

	FoodMenu fakeMenu = new FoodMenu(UUID.randomUUID(), RandomString.make(), RandomString.make(), RandomString.make());
	UUID fakeUuid2 = UUID.randomUUID();
	
	CreateOrder createOrder = mock(CreateOrder.class);
	CustomerOrderingCreateOrderUsecase customerOrderingCreateOrderUsecase = new CustomerOrderingCreateOrderUsecase(createOrder);

	@Test
	public void canCreateOrder() {
		Collection<FoodMenu> orderingFoodMenus = new ArrayList<FoodMenu>();
		orderingFoodMenus.add(fakeMenu);
		
		Order expected = givenOrderCreated(orderingFoodMenus);
		Order actual = customerOrderingCreateOrderUsecase.createOrder(orderingFoodMenus);
		assertThat(actual.getId()).isEqualTo(expected.getId());
	}

	private Order givenOrderCreated(Collection<FoodMenu> orderingFoodMenus) {
		Order newOrder = new Order(orderingFoodMenus);
		when(createOrder.createOrder(orderingFoodMenus)).thenReturn(newOrder);
		return newOrder;
	}
	

	@Test
	public void errorWhenFoodMenuIsNotFoundInOrder() throws Exception {
		Collection<FoodMenu> orderingFoodMenus = new ArrayList<FoodMenu>();
		givenNoFoodMenuFoundInOrder(orderingFoodMenus);
		assertThatExceptionOfType(NoFoodMenuInNewOrderException.class).isThrownBy(() -> customerOrderingCreateOrderUsecase.createOrder(orderingFoodMenus));
	}

	private void givenNoFoodMenuFoundInOrder(Collection<FoodMenu> orderingFoodMenus) {		
		when(createOrder.createOrder(orderingFoodMenus)).thenThrow(new NoFoodMenuInNewOrderException());
		
	}


}
