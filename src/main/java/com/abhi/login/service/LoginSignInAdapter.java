package com.abhi.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class LoginSignInAdapter implements SignInAdapter {
	
	@Autowired
	private UserService userService;
	
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
    	
    	
    	System.out.println("Here here here!!!");
    	System.out.println(localUserId);
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(localUserId, null, null));
    	
        
        return null;
    }
}

