package com.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.dao.UsersRepository;
import com.demo.entity.UsersEntity;
import com.demo.service.UserService;

@Service
public class UsersServiceImpl implements UserService {

	@Autowired
	UsersRepository usersRepo;

	@Override
	public UsersEntity login(String userName, String password) {
		UsersEntity findUserByName = usersRepo.findByUserName(userName);
		if (findUserByName == null) {
			throw new UsernameNotFoundException("error");
		}
		return findUserByName;
	}

}
