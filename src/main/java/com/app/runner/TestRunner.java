package com.app.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

@Component
public class TestRunner implements ApplicationRunner{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("From run method in TestRunner class.");
		
		System.out.println("------ To get all records by findAll() method ------");
		List<Employee> employeeList1 = employeeRepository.findAll();
		
		employeeList1.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("------ To get all records by findAll(Sort sort) method ------");
		List<Employee> employeeList2 = employeeRepository.findAll(Sort.by(Order.asc("employeeName")));
		
		employeeList2.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("--------- To get records by findAll(Pageable pageable) method ---------");
		Page<Employee> page1 = employeeRepository.findAll(Pageable.ofSize(3));
		page1.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("--------- To get records by findAll(Pageable pageable) methed ---------");
		Page<Employee> page2 = employeeRepository.findAll(PageRequest.of(2, 1));
		page2.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("------- To get records by findAllById(Iterable<ID> ids) method ------");
		List<Integer> idlist1 = Arrays.asList(2, 3, 5);
		List<Employee> employeeList3 = employeeRepository.findAllById(idlist1);
		
		employeeList3.forEach(e->{
			System.out.println(e.getEmployeeId()+" --> "+e.getEmployeeName()+"["+e.getEmployeeCode()+"]");
		});
		
		System.out.println("------ To get record by findById(ID id) method -------");
		Optional<Employee> employeeOptional1 = employeeRepository.findById(5);
		if(employeeOptional1.isPresent()) {
			Employee employee = employeeOptional1.get();
			System.out.println(employee.getEmployeeId()+" --> "+employee.getEmployeeName()+"["+employee.getEmployeeCode()+"]");
		}
		
	}

}
