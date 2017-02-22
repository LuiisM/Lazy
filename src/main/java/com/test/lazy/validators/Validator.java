package com.test.lazy.validators;

import java.util.Iterator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.test.lazy.domains.Employee;

public class Validator implements org.springframework.validation.Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {

		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "name.required", "enter a name");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "data", "data.required", "enter the data");
		Employee emp = (Employee) target;
		
		if (!emp.getData().isEmpty()) {
			if (emp.getData().get(0) > 500 || emp.getData().get(0) < 1) {
				e.rejectValue("days", "days between 1 and 500");
			}
			for (Iterator<Integer> it = emp.getData().iterator(); it.hasNext();) {
				if ((int) it.next() > 100)
					e.rejectValue("data", "data.required", "weight value between 1 and 100");
			}
		}
		
	}
}