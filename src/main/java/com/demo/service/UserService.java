package com.demo.service;

import com.demo.entity.UsersEntity;

public interface UserService {

	UsersEntity login(String userName, String password);
}
