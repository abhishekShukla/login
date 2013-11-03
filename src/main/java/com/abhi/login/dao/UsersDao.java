package com.abhi.login.dao;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
@Transactional
public class UsersDao implements IUsersDao {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public void create(User user){
		
		System.out.println(user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	@Override
	public boolean exists(String username) {
		// TODO Auto-generated method stub
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User)criteria.uniqueResult();
		
		return (user != null); 
		
	}
	
	public User getUser(String username){
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User)criteria.uniqueResult();
		
		return user;
	}

	@Override
	public boolean verifyLogin(String username, String password) {
		
		User user = this.getUser(username);
	    return (user != null) &&  passwordEncoder.matches(password, user.getPassword());
		
	}

	@Override
	public User emailVerify(String access) {
		
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("uuid", access));
		User user = (User)criteria.uniqueResult();
		
		if(user != null){
			user.setEnabled(true);
		}
		
		return user;
	}

}
