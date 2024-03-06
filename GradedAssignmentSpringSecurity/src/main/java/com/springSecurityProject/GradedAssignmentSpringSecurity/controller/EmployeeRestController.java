package com.springSecurityProject.GradedAssignmentSpringSecurity.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.Employee;
import com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices.AppUserService;
import com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices.EmployeeService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class EmployeeRestController {
	
	@Autowired
	EmployeeService service;
	
	
	@PostMapping("/AddEmployee")
	public String addEmployee(@RequestParam String firstname,@RequestParam String lastname,@RequestParam String email,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole="admin";

		boolean roleFound=false;
		System.out.println("Curent login by:"+authentication.getName());

		Collection<?extends GrantedAuthority>grantedRoles=auth.getContext().getAuthentication().getAuthorities();
		for(int i=0;i<grantedRoles.size();i++)
		{
			
			  String role1=grantedRoles.toArray()[i].toString();
			  System.out.println("my role :"+role1);
			 
			if(role1.equalsIgnoreCase(acceptedRole))
			{
				roleFound=true;
			}
		}
		if(roleFound==true)
		{
		service.insert(Employee.builder().firstname(firstname).lastname(lastname).email(email).build());
		return "Employee Added";
		}
		else
		{
			return "Not Added";
		}
	}
	@PutMapping("/UpdateEmployee")
	public String updateEmployee(@RequestParam int id,@RequestParam String firstname,@RequestParam String lastname,@RequestParam String email,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole="admin";

		boolean roleFound=false;
		System.out.println("Curent login by:"+authentication.getName());

		Collection<?extends GrantedAuthority> 		grantedRoles=auth.getContext().getAuthentication().getAuthorities();
		for(int i=0;i<grantedRoles.size();i++)
		{
			
			  String role1=grantedRoles.toArray()[i].toString();
			  System.out.println("my role :"+role1);
			 
			if(role1.equalsIgnoreCase(acceptedRole))
			{
				roleFound=true;
			}
		}
		if(roleFound==true)
		{
		service.update(Employee.builder().id(id).firstname(firstname).lastname(lastname).email(email).build());
		return "Employee id :"+id+"\nEmployee firstname :"+firstname+"\nEmployee lastname:"+lastname+"\nEmail :"+email+"\n Employee Updated";
		}
		else {
			return "Not Updated";
		}
	}
	@DeleteMapping("/DeleteEmployee")
	public String deleteEmployee(@RequestParam int id,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole="admin";

		boolean roleFound=false;
		System.out.println("Curent login by:"+authentication.getName());

		Collection<?extends GrantedAuthority> 		grantedRoles=auth.getContext().getAuthentication().getAuthorities();
		for(int i=0;i<grantedRoles.size();i++)
		{
			
			  String role1=grantedRoles.toArray()[i].toString();
			  System.out.println("my role :"+role1);
			 
			if(role1.equalsIgnoreCase(acceptedRole))
			{
				roleFound=true;
			}
		}
		if(roleFound==true)
		{
		service.delete(new Employee(id, null, null, null));
		return "Deleted Employee Id :"+id;
		}
		else
		{
			return "Not Deleted";
		}
	}
	@GetMapping("/ShowAllEmployee")
	public List<Employee> showAllEmployee()
	{
		
		return  service.getAll();
	}
	@Operation(summary = "Send Order ASC for ascending order ,others DESC for Descending order.")
	@GetMapping("/ShowEmployeeByOrder")
	public List<Employee> showAllEmployee(@RequestParam String order)
	{
		if(order.equalsIgnoreCase("ASC") )
		{
		return  service.getSortByFirstName(Direction.ASC);
		}
		else if(order.equalsIgnoreCase("DESC") )
		{
			return  service.getSortByFirstName(Direction.DESC);
		}
		else
		{
			return null;
		}
	}
	@GetMapping("/ShowEmployeeById")
	public Employee showEmployeeById(@RequestParam int id)
	{
		
		return  service.findById(id);
	}
	@GetMapping("/SearchEmployee")
	public List<Employee>  SearchEmployee(@RequestParam String search)
	{
		
		return  service.filter(search);
	}
	
}
