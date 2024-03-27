package com.example.cms.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cms.userrepository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustumUserDetailService implements UserDetailsService{
     
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repository.findByEmail(username).map(user->new CustumUserDetails(user))
				.orElseThrow(()->new UsernameNotFoundException("User not found...!"));
	}

}
