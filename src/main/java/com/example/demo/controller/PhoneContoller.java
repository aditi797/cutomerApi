package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MessageResponse;

@RestController
public class PhoneContoller {	

	@GetMapping("/api/verify-phone-number")
	public MessageResponse isCustomerEmailValid(@RequestParam  String phone)
	{
		MessageResponse response = new MessageResponse();
		List<String> message = new ArrayList<String>();
		if (phone == null)
		{
			message.add("phoneNumber is null");
			response.setMessage(message);
			response.setValid(false);
		}
		else 
		{
			message.add("phoneNumber is verified");
			response.setMessage(message);
			response.setValid(true);
		}
			
			 
		return response;
		
	}
    
	

}
