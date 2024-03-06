package com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices;



import org.springframework.data.jpa.repository.JpaRepository;


import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.Employee;



public interface EmployeeRepositery extends JpaRepository<Employee, Integer> {
	
	

}
