package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String>{

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		if (phoneNumber==null) {
			return false;
		}
		//Google this pattern
		if (phoneNumber.matches("^\\d{10}$")) {
			return true;
					 
		}
		return false;
	}

}
