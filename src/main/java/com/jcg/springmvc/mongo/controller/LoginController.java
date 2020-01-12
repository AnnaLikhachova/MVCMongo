package com.jcg.springmvc.mongo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcg.springmvc.mongo.model.User;
import com.jcg.springmvc.mongo.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
    private UserService userService;

	/**
     * This method returns login page.
     */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(@ModelAttribute("userAttr") User user) {
		return "/login";
	}

	 /**
     * This method for user authorisation.
     */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String submit(@ModelAttribute("userAttr") User user,
						 @RequestParam(required = false) String email,
						 @RequestParam(required = false) String password,
						 HttpSession session,
						 HttpServletRequest req,
						 Model model) {
		
		user = userService.findUserByEmail(email);
		
		if (user == null) {			
			return "/login";
		} else {
			model.addAttribute("loggedUser", user);
			model.addAttribute("email", user.getEmail());
			model.addAttribute("password", user.getPassword());
			return "profile";
		}		
	}
}
