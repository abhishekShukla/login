package com.abhi.login.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao implements IUsersDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean create(User user){
		
			BeanPropertySqlParameterSource userParams = new BeanPropertySqlParameterSource(user);
			
			jdbc.update("insert into users (username, password, email, enabled)" +
					" values (:username, :password, :email, :enabled)", userParams);
			
			return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", userParams) == 1;		
		
	}

}
