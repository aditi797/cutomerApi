package com.example.demo.model;

import java.util.List;

public class MessageResponse {
	
	private List<String> message;
	private Boolean valid;
	
	
	public MessageResponse()
	{
		super();
	}
	public MessageResponse(List<String> message,Boolean valid) {
		super();
		this.message = message;
		this.valid = valid;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	  public boolean isValid() 
	  { return valid; }

	  public void setValid(boolean valid) 
	  { this.valid = valid; }
	 

}
