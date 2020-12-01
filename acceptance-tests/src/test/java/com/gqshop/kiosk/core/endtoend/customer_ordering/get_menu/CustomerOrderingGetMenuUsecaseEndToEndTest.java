package com.gqshop.kiosk.core.endtoend.customer_ordering.get_menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.googlecode.yatspec.junit.SpecRunner;
import com.gqshop.kiosk.core.entity.FoodMenu;
import com.gqshop.kiosk.dataprovider.database.foodmenu.FoodMenuDatabaseMockDataProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@RunWith(SpecRunner.class)
@Configuration
@Component
public class CustomerOrderingGetMenuUsecaseEndToEndTest implements CommandLineRunner{
	
	@Autowired
	FoodMenuDatabaseMockDataProvider foodMenuDatabaseMockDataProvider;
//	FoodMenuDatabaseMockDataProvider foodMenuDatabaseMockDataProvider = mock(FoodMenuDatabaseMockDataProvider.class);
	
	
	@Test
	public void reallySimpleExample() throws Exception {
		assertThat("The quick brown fox".contains("fox"), is(true));
	}
	

    @Test
    public void returnsTheDetailsOfADevice() throws Exception {
        givenOneFoodMenuInOurModel();
        var httpResult = whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice();
        thenTheDetailsOfTheDeviceAreReturned(httpResult);
    }
    

    @SuppressWarnings("unchecked")
	private void givenOneFoodMenuInOurModel() {
    	
    	if(foodMenuDatabaseMockDataProvider == null) {
    		System.out.println("foodMenuDatabaseMockDataProvider is null");
    		
    		tmpInitFoodMenuDatabaseMockDataProvider();//adhoc
    		
    	}else {
    		System.out.println("foodMenuDatabaseMockDataProvider is not null");
    	}
    	
//    	new ArrayList<FoodMenu>()
    	Collection<FoodMenu> foodMenuList = new ArrayList<FoodMenu>();
    	foodMenuList.add(new FoodMenu("kimchi","korean spicy sidedish"));
		try {
//			foodMenuList = (Collection<FoodMenu>)FieldUtils.readField(foodMenuDatabaseMockDataProvider, "foodMenuList", true);
			
			FieldUtils.writeDeclaredField(foodMenuDatabaseMockDataProvider, "foodMenuList", foodMenuList, true);

//			var field = FieldUtils.getField(FoodMenuDatabaseMockDataProvider.class, "foodMenuList", true);
//			FieldUtils.writeField(field, foodMenuDatabaseMockDataProvider, foodMenuList, true);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
//        exchangeDatabaseDataProvider.insert(new Exchange(EXCHANGE_CODE, EXCHANGE_NAME, EXCHANGE_POSTCODE));
//        broadbandAccessDeviceDatabaseDataProvider.insert(EXCHANGE_CODE, HOSTNAME, SERIAL_NUMBER, DEVICE_TYPE);
//        log("Device in model", "Exchange Code: " + EXCHANGE_CODE + "\nHostname: " + HOSTNAME + "\nSerial Number: " + SERIAL_NUMBER + "\nType: " + DEVICE_TYPE);
    }

    private HashMap<String,String> whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice() throws UnirestException {
//        String apiPath = GetBroadbandAccessDeviceEndpoint.API_PATH.replace("{hostname}", HOSTNAME);
        String apiUrl = "http://localhost:9000/api/foodmenu";
//        log("API Url", apiUrl);

        HttpResponse<String> httpResponse = Unirest.get(apiUrl).asString();
        HashMap<String,String> resultMap = new HashMap<String,String>();
        
        resultMap.put("responseStatus", Integer.toString(httpResponse.getCode()));        
        resultMap.put("responseContent", httpResponse.getBody());
//        
//        int responseStatus = httpResponse.getCode();
//        System.out.println("Response Status", responseStatus);
//        
//
//        String responseContent = httpResponse.getBody();
//        System.out.print("Response Content", formatJson(responseContent));
        return resultMap;
    }

    private void thenTheDetailsOfTheDeviceAreReturned(HashMap<String,String> resultMap) {
        assertThat((String)resultMap.get("responseStatus")).isEqualTo("200");

        
        System.out.println(resultMap.get("responseStatus"));
        System.out.println(resultMap.get("responseContent"));
        
//        String expectedResponse =
//                "{\n" +
//                "  \"exchangeCode\":\"" + EXCHANGE_CODE + "\",\n" +
//                "  \"hostname\":\"" + HOSTNAME + "\",\n" +
//                "  \"serialNumber\":\"" + SERIAL_NUMBER + "\",\n" +
//                "  \"type\":\"" + DEVICE_TYPE.name() + "\"\n" +
//                "}";
//        JSONAssert.assertEquals(expectedResponse, responseContent, false);
    }


	private void tmpInitFoodMenuDatabaseMockDataProvider() {
		if(null == foodMenuDatabaseMockDataProvider) {
			foodMenuDatabaseMockDataProvider = new FoodMenuDatabaseMockDataProvider(new ArrayList<FoodMenu>());
		}
		
	}
    
	@Override
	public void run(String... args) throws Exception {
		//bean was not genning. so i forcifully made it.
		tmpInitFoodMenuDatabaseMockDataProvider();	
	}

}
