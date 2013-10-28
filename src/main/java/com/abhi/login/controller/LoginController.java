package com.abhi.login.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abhi.login.dao.User;
import com.abhi.login.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	/*
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	*/

	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/newAccount")
	public String showNewAccount(Model model){
		model.addAttribute("user", new User());
		return "newAccount";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.POST)
	public String showCreateAccount(Model model,@Valid User user, BindingResult result){
		
		if(result.hasErrors()){
			return "createAccount";
		}
		
		user.setAuthority("user");
		user.setEnabled(true);
		
		userService.create(user);
		
		return "accountCreated";
	}
}
