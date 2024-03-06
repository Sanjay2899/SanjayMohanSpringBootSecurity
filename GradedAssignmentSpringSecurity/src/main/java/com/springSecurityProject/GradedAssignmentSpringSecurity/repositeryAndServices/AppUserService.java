package com.springSecurityProject.GradedAssignmentSpringSecurity.repositeryAndServices;

import java.util.HashSet; 
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springSecurityProject.GradedAssignmentSpringSecurity.Model.AppUser;


@Service
public class AppUserService implements UserDetailsService {
	
	@Autowired
	AppUserRepositery repo;
	public void insert(AppUser appUser)
	{
		repo.save(appUser);
	}
	public void update(AppUser appUser)
	{
		repo.save(appUser);
	}
	public List<AppUser> getAll()
	{
		return repo.findAll();
	}
	
	public void delete(AppUser appUser)
	{
		repo.delete(appUser);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<AppUser> appUser=repo.findByName(username);
		//we are coverting the role from DB to grantedAuthorities of UserDetailsService
		
		Set<GrantedAuthority> grantedAuthorities=new HashSet<GrantedAuthority>();
		
		for(String temp:appUser.get().getAuthorities())
		{
			GrantedAuthority ga=new SimpleGrantedAuthority(temp);
			grantedAuthorities.add(ga);
		}
		//coverting appuser to user from user security 
		User user=new User(username, appUser.get().getPassword(),grantedAuthorities);
		return user;
	}
	
}
