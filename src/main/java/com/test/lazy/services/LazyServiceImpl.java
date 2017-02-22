package com.test.lazy.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.lazy.domains.Employee;
import com.test.lazy.repositories.LazyRepository;

@Service("lazyService")
public class LazyServiceImpl implements LazyService {

	@Autowired
	private LazyRepository lazyRepository;
	/*
	 * 
	 */
	public void LazyService() {

	}
	/* Get data from employee
	 * @param Employee 
	 *   data int[]
	 *   days int
	 *   name String
	 * @return ResponseEntity<String>
 	 * @see com.test.lazy.services.LazyService#getData(com.test.lazy.domains.Employee)
	 */
	@Override
	public List<Integer> getResult(Employee emp) {
		int days = emp.getData().get(0);
		List<Integer> result = new ArrayList<>();
		List<Integer> values = emp.getData().subList(1, emp.getData().size());
		int initial = 0;
		for (int i = 0; i < days; i++) {
			int last = (initial + 1) + values.get(initial);
			List<Integer> weight = values.subList(initial + 1, last);
			Collections.sort(weight);
			int trips = 0;
			int left = 0;
			int right = values.get(initial) - 1;
			for (int count = 1; count < right; count--) {
				while (count * weight.get(right) < 50)
					count++;
				if (count <= right - left + 1)
					trips++;
				left += count - 1;
				right--;
			}
			initial = last;
			result.add(trips);
		}
		if(!result.isEmpty()){
			emp.setCreated_at(new Date());
			lazyRepository.save(emp);
		}
		return result;
	}

}
