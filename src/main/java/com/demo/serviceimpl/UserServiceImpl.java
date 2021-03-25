package com.demo.serviceimpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dao.UsersRepository;
import com.demo.entity.UsersEntity;
import com.demo.model.ResponseDataModel;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userRepo;

	@Override
	public ResponseDataModel addUser(UsersEntity usersEntity) throws Exception {
		UsersEntity user = userRepo.findByUserName(usersEntity.getUserName());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		int responseCode = 0;
		String responseMsg = StringUtils.EMPTY;
		if (user != null) {
			responseCode = 1;
			responseMsg = "Duplicate username";
		} else {

			UsersEntity addUser = new UsersEntity();
			addUser.setUserName(usersEntity.getUserName());
			addUser.setPassword(encoder.encode(usersEntity.getPassword()));
			userRepo.saveAndFlush(addUser);
			responseCode = 100;
			responseMsg = "User is added sucess";
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

}
