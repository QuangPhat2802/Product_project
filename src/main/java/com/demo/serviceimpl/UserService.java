package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.dao.UsersRepository;
import com.demo.entity.UsersEntity;
import com.demo.secutiry.CustomUserDetails;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UsersRepository usersRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersEntity users = usersRepo.findByUserName(username);
		if (users == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		return new CustomUserDetails(users);
	}

}
