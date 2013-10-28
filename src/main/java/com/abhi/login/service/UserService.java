package com.abhi.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.login.dao.IUsersDao;
import com.abhi.login.dao.User;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUsersDao usersDao;
	
	public void create(User user){
		
		//DebugUtils.transactionRequired("UserService.create");
		usersDao.create(user);
	}
	
}
