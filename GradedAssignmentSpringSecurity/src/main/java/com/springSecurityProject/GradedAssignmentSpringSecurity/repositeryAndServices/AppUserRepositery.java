package com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices;

import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.AppUser;



public interface AppUserRepositery extends JpaRepository<AppUser, Integer> {
	
	Optional<AppUser> findByName(String name);

}
