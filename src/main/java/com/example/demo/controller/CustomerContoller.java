package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.MessageResponse;
import com.example.demo.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customers")
public class CustomerContoller {	

	@Autowired
	private CustomerServiceImpl customerService;
	
	@PostMapping("")
	public ResponseEntity<Object> onboardCustomer(@Valid @RequestBody Customer customer) throws Exception
	{
		//Before onboarding - call multiple API to validate the details like with email provider
		//phone number provider
		MessageResponse emailApiResponse;
		MessageResponse phoneApiResponse;
		CompletableFuture<MessageResponse> responseFromEmailAPI = customerService.isCustomerEmailValid(customer.getEmail());
		CompletableFuture<MessageResponse> responseFromPhoneAPI = customerService.isCustomerPhoneValid(customer.getPhoneNumber());
		CompletableFuture.allOf(responseFromEmailAPI,responseFromPhoneAPI).join();

		try
		{
			emailApiResponse =responseFromEmailAPI.get(20, TimeUnit.MILLISECONDS);
			phoneApiResponse= responseFromPhoneAPI.get(20, TimeUnit.MILLISECONDS);
			
		}
		catch (Exception e) {
			throw e;
		}
		if(emailApiResponse.isValid() && phoneApiResponse.isValid()) {
			final Customer  newCustomer = customerService.addCustomer(customer);
			return ResponseEntity.ok(newCustomer);
		}
		else
		{
			List<MessageResponse> response = new ArrayList<MessageResponse>();
			response.add(emailApiResponse);
			response.add(phoneApiResponse);
			return ResponseEntity.ok(response) ;
		}
	}
	

}
