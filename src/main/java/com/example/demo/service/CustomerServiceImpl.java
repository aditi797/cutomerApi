package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Customer;
import com.example.demo.model.MessageResponse;


@Service
public class CustomerServiceImpl{
		
	@Autowired
	private CustomerDao customerDao;
	
	private final RestTemplate restTemplate;
	
	public CustomerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
	    this.restTemplate = restTemplateBuilder.build();
	  }

	
	public Customer addCustomer(Customer customer) {		
		return customerDao.save(customer);
	}

	
	@Async
	public CompletableFuture<MessageResponse> isCustomerEmailValid(String emailId) {
		String url ="http://localhost:8080/api/verify-emaid-id?emailId="+emailId;
		MessageResponse response = restTemplate.getForObject(url, MessageResponse.class);
		return CompletableFuture.completedFuture(response);
		
	}

	
	@Async
	public CompletableFuture<MessageResponse> isCustomerPhoneValid(String phone) {
		String url ="http://localhost:8080/api/verify-phone-number?phone="+phone;
		MessageResponse response = restTemplate.getForObject(url, MessageResponse.class);
		return CompletableFuture.completedFuture(response);
		
	}

	

}
