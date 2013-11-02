package com.abhi.login.dao;

public interface IUsersDao {
	
	public void create(User user);

	public boolean exists(String username);
	
	public User getUser(String username);
	
	public boolean verifyLogin(String username, String password);
	
}
