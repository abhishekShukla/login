package com.abhi.login.dao;

public interface IUsersDao {
	
	public boolean create(User user);

	public boolean exists(String username);
	
}
