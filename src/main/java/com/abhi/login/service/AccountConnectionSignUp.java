package com.abhi.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.abhi.login.dao.IUsersDao;
import com.abhi.login.dao.User;

public class AccountConnectionSignUp implements ConnectionSignUp {
	
	@Autowired
	private IUsersDao usersDao;
	
	@Override
	public String execute(Connection<?> connection) {
		// TODO Auto-generated method stub
		UserProfile socialMediaProfile = connection.fetchUserProfile();
        
		System.out.println("In AUTO Signup");
		User user = null;
        if(!usersDao.exists(socialMediaProfile.getFirstName() + socialMediaProfile.getLastName())){
        	user = new User();
            
            System.out.println(socialMediaProfile.getFirstName() + socialMediaProfile.getLastName());
            System.out.println("example@example.com");
            
            user.setUsername(socialMediaProfile.getFirstName() + socialMediaProfile.getLastName());
            user.setPassword(socialMediaProfile.getUsername());
            user.setEmail("example@example.com");
            user.setEnabled(true);
            user.setAuthority("user");
            user.setUuid(java.util.UUID.randomUUID().toString());
            
            
            usersDao.create(user);
            
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), null, null));
        } else {
        	System.out.println("In else! AUTO");
        	user = usersDao.getUser(socialMediaProfile.getFirstName() + socialMediaProfile.getLastName());
        	SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), null, null));
        }
        
		return user.getUsername();
	}

}
