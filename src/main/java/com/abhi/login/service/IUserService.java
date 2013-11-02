package com.abhi.login.service;

import com.abhi.login.dao.User;

public interface IUserService {
	
	public void create(User user);

	public boolean exists(String username);
	
	public boolean verifyLogin(String username, String password);
	
}
