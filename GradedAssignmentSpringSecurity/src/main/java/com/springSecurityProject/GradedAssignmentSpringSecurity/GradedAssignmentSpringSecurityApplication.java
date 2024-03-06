package com.springSecurityProject.GradedAssignmentSpringSecurity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.AppUser;
import com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices.AppUserService;


@SpringBootApplication
public class GradedAssignmentSpringSecurityApplication implements CommandLineRunner {

	@Autowired
	AppUserService service;
	public static void main(String[] args) {
		SpringApplication.run(GradedAssignmentSpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * Set<String> authAmin=new HashSet<String>(); authAmin.add("Admin");
		 * PasswordEncoder en=new BCryptPasswordEncoder();
		 * 
		 * service.insert(AppUser.builder().name("Admin").password(en.encode("Adminpass"
		 * )).authorities(authAmin).build());
		 */
	}

}
