package com.abhi.login.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao implements IUsersDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean create(User user){
		
			MapSqlParameterSource userParams = new MapSqlParameterSource();
			userParams.addValue("username", user.getUsername());
			userParams.addValue("password", passwordEncoder.encode(user.getPassword()));
			userParams.addValue("email", user.getEmail());
			userParams.addValue("enabled", user.isEnabled());
			userParams.addValue("authority", user.getAuthority());
			
			jdbc.update("insert into users (username, password, email, enabled)" +
					" values (:username, :password, :email, :enabled)", userParams);
			
			return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", userParams) == 1;		
		
	}

	@Override
	public boolean exists(String username) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select count(*) from users where username =:username", 
					new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

}
