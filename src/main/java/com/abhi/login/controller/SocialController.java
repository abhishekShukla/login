package com.abhi.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.abhi.login.service.IUserService;

@Controller
public class SocialController {
	
	@Autowired
	private IUserService userService;
	
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String showLoginPage() {
    	System.out.println("Here!");
        return "login";
    }
    
    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signupForm(WebRequest request) {
        
        System.out.println("Here in signup. Do not know what to do!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        return "redirect:/";
    }
	
}
