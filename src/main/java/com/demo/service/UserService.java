package com.demo.service;

import com.demo.entity.UsersEntity;
import com.demo.model.ResponseDataModel;

public interface UserService {

	ResponseDataModel  addUser(UsersEntity usersEntity) throws Exception;
}
