package com.springSecurityProject.GradedAssignmentSpringSecurity.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.AppUser;
import com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices.AppUserService;

@RestController
public class RoleController {
	
	@Autowired
	AppUserService service;
	
	@PostMapping("/AddNewUser")
	public String AddNewUser(@RequestParam String username,@RequestParam String password,@RequestParam String role,Authentication authentication,SecurityContextHolder auth) {
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
		Set<String> auth1=new HashSet<String>();
		auth1.add(role);

		PasswordEncoder en=new BCryptPasswordEncoder();
		System.out.println("Curent login by:"+authentication.getName());
		service.insert(AppUser.builder().name(username).password(en.encode(password)).authorities(auth1).build());
		return "User Added";
		}
		else {
			return "Not Added";
		}
	}
	@GetMapping("ShowAllUser")
	public List<AppUser> ShowAllUser()
	{
		return service.getAll();
	}

}
