package com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices;


import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.Employee;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepositery repo;
	public void insert(Employee employee)
	{
		repo.save(employee);
	}
	public void update(Employee employee)
	{
		repo.save(employee);
	}
	public List<Employee> getAll()
	{
		return repo.findAll();
	}
	
	public void delete(Employee employee)
	{
		repo.delete(employee);
	}
	public List<Employee> getSortByFirstName(Direction direction)
	{
		return repo.findAll(Sort.by(direction,"firstname"));
	}
	public Employee findById(int id)
	{
		Optional<Employee> opt=repo.findById(id);
		Employee emp=null;
		if(opt!=null)
		{
			emp=opt.get();
			
		}
		
		return emp;
	}
	public List<Employee> filter(String searchkey)
	{
		//1.create a dummy object based on the searchkey
		Employee dummy=new Employee();
		dummy.setFirstname(searchkey);
		
		//2.create Example JPA-Where
		ExampleMatcher  exampleMatcher=exampleMatcher=ExampleMatcher.matching().withMatcher("firstname",ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id","lastname","email");
		
		
		Example<Employee> example=Example.of(dummy,exampleMatcher);
		
		return repo.findAll(example);
		
	}
	
	
	
}
