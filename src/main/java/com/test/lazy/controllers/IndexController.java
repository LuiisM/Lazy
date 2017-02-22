package com.test.lazy.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.lazy.domains.Employee;
import com.test.lazy.services.LazyService;
import com.test.lazy.validators.Validator;

@RestController
public class IndexController {

	@Autowired
	private LazyService lazy;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new Validator());
	}

	@CrossOrigin
	@RequestMapping(value = "/information", method = RequestMethod.POST)
	public ResponseEntity<?> getData(@RequestBody @Valid Employee employee) {
		if (!lazy.getResult(employee).isEmpty()) {
			return new ResponseEntity<>(lazy.getResult(employee), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Not found", HttpStatus.BAD_REQUEST);
	}

}
