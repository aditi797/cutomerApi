package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MessageResponse;

@RestController
public class EmailContoller {	

	/**
	 * emailId should  belong to gmail.com
	 * @param emailId
	 * @return
	 */
	@GetMapping("/api/verify-emaid-id")
	public MessageResponse isCustomerEmailValid(@RequestParam  String emailId)
	{
		MessageResponse response = new MessageResponse();
		List<String> message = new ArrayList<String>();
		if (emailId != null && emailId.contains("gmail.com"))
		{
			message.add("emailId is verified");
			response.setMessage(message);
			response.setValid(true);
		}
		else 
		{
			message.add("Sorry, emailId is not verified");
			response.setMessage(message);
			response.setValid(false);
		}
			 
		return response;
		
	}
    
	

}
