package com.test.lazy.repositories;

import org.springframework.data.repository.CrudRepository;

import com.test.lazy.domains.Employee;


public interface LazyRepository extends CrudRepository<Employee, Integer>{

}
