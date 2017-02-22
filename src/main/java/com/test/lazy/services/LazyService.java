package com.test.lazy.services;

import java.util.List;

import com.test.lazy.domains.Employee;

public interface LazyService {

	List<Integer> getResult(Employee employee);
}
