package com.jcg.springmvc.mongo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jcg.springmvc.mongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.jcg.springmvc.mongo.User;

/**
 * The controller class for registration page.
 */
@Controller
public class RegistrationController {
	
	
/*	@Autowired
    private UserValidator userValidator;*/
	
	@Autowired
    private UserService userService;
	     
	@Autowired
	MessageSource messageSource;
	
	/**
     * This method returns registration page.
     */
	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}
	
	/**
     * This is POST method for user registration.
     */
	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String registrationForm(@ModelAttribute("user") User user,
            SessionStatus status, HttpSession session, HttpServletRequest req,
			 Model model) {
		
		//userValidator.validate(user, bindingResult);
		session = req.getSession();
		session.setAttribute("loggedUser", user);
        //userService.add(user);
        model.addAttribute("user", user);
		return  "welcome";
	}
	
}
