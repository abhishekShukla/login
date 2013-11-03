package com.abhi.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhi.login.dao.FormValidationGroup;
import com.abhi.login.dao.User;
import com.abhi.login.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;


	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/logout")
	public String showLogout(){
		return "logout";
	}
	
	@RequestMapping("/newAccount")
	public String showNewAccount(Model model){
		model.addAttribute("user", new User());
		return "newAccount";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.POST)
	public String showCreateAccount(Model model,@Validated(FormValidationGroup.class) User user, 
				BindingResult result){
		
		if(result.hasErrors()){
			return "newAccount";
		}
		
		if(userService.exists(user.getUsername())){
			result.rejectValue("username", "Duplicate.user.username", "Username already exists");
			return "newAccount";
		}
		user.setUuid(java.util.UUID.randomUUID().toString());
		user.setAuthority("user");
		user.setEnabled(false);
		
		try{
			userService.create(user);
			userService.email(user);
		} catch(DuplicateKeyException e){
			result.rejectValue("username", "Duplicate.user.username");
			return "newAccount";
		}
		
		return "accountCreated";
	}
	
	@RequestMapping(value="/verify", method=RequestMethod.GET)
	public String showVerifiedAccount(Model model, @RequestParam String id){
		
		User user = userService.emailVerify(id);
		
		if(user != null && user.isEnabled()){
			return "home";
		} else {
			return "accountCreated";
		}
		
		
	}
}
