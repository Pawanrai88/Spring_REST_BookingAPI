package com.APIPractise;


import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.APIPractise.bean.Booking;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jayway.jsonpath.internal.filter.ValueNodes.JsonNode;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTesting {
	
	@Test @Order(1)
	public void getAllBookingsTest() throws JSONException {
		
		String expected = "[\r\n"
				+ "    {\r\n"
				+ "        \"bookingId\": 8754,\r\n"
				+ "        \"firstName\": \"Anshul\",\r\n"
				+ "        \"lastName\": null,\r\n"
				+ "        \"origin\": \"India\",\r\n"
				+ "        \"destination\": \"Nepal\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"bookingId\": 9856,\r\n"
				+ "        \"firstName\": \"Rahul\",\r\n"
				+ "        \"lastName\": \"Verma\",\r\n"
				+ "        \"origin\": \"India\",\r\n"
				+ "        \"destination\": \"Pakistan\"\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate resttemp=new TestRestTemplate();
		ResponseEntity<String> response =resttemp.getForEntity("http://localhost:8080/booking",String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test @Order(2)
	public void getBookingByIdTest() throws JSONException {
		
		String expected = "{\r\n"
				+ "    \"bookingId\": 9856,\r\n"
				+ "    \"firstName\": \"Rahul\",\r\n"
				+ "    \"lastName\": \"Verma\",\r\n"
				+ "    \"origin\": \"India\",\r\n"
				+ "    \"destination\": \"Pakistan\"\r\n"
				+ "}";
		
		TestRestTemplate resttemp=new TestRestTemplate();
		ResponseEntity<String> response =resttemp.getForEntity("http://localhost:8080/booking/9856",String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test @Order(3)
	public void createBookingTest() throws JSONException {
		Booking booking = new Booking(1234,"Abhay","Sharma","India","Sweden");
		TestRestTemplate resttemp=new TestRestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Booking> request=new HttpEntity<Booking>(booking,header);
		
       
		ResponseEntity<String> response =resttemp.postForEntity("http://localhost:8080/booking", request, String.class);
		System.out.println(response);
		String expected = "{\r\n"
			//	+ "    \"bookingId\": 1234,\r\n"
				+ "        \"firstName\": \"Abhay\",\r\n"
				+ "        \"lastName\": \"Sharma\",\r\n"
				+ "        \"origin\": \"India\",\r\n"
				+ "        \"destination\": \"Sweden\"\r\n"
				+ "    }\r\n";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test @Order(4)
	public void updateBookingTest() throws JSONException {
		Booking booking = new Booking(8754,"Anshul","Sharma","India","Nepal");
		TestRestTemplate resttemp=new TestRestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Booking> request=new HttpEntity<Booking>(booking,header);
		
       
		ResponseEntity<String> response =resttemp.exchange("http://localhost:8080/booking/8754",HttpMethod.PUT, request, String.class);
		System.out.println(response);
		String expected = "{\r\n"
			//	+ "    \"bookingId\": 1234,\r\n"
				+ "        \"firstName\": \"Anshul\",\r\n"
				+ "        \"lastName\": \"Sharma\",\r\n"
				+ "        \"origin\": \"India\",\r\n"
				+ "        \"destination\": \"Nepal\"\r\n"
				+ "    }\r\n";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test @Order(5)
	public void deleteBookingTest() throws JSONException {
		
		TestRestTemplate resttemp=new TestRestTemplate();
		resttemp.delete("http://localhost:8080/booking/8754");
		
	}

}
