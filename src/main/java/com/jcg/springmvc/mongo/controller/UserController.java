package com.jcg.springmvc.mongo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.jcg.springmvc.mongo.User;
import com.jcg.springmvc.mongo.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value = { "/form" }, method = RequestMethod.GET)
	public String showForm(ModelMap model) {		
		User user = new User();
		model.addAttribute("userAttr", user);
		return "form";
	}
	
	@RequestMapping(value = { "/form" }, method = RequestMethod.POST)
	public String saveUser(ModelMap model, @ModelAttribute("userAttr") User user) {		
		if(user.getId() != null && !user.getId().trim().equals("")) {
			userService.edit(user);
		} else {
			userService.add(user);
		}
		return "form";
	}
	
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String showUserList(ModelMap model) {		
		User user = new User();
		model.addAttribute("userAttr", user);
		log.debug("Request to fetch all users from the mongo database");
		List<User> userList = userService.getAll();		
		model.addAttribute("users", userList);
		return "welcome";
	}
	
/*	@RequestMapping(value = { "/welcome" }, method = RequestMethod.POST)
	public String editUser(ModelMap model) {		
		return "form";
	}*/
	
/*	*//**
     * This method will provide the medium to update an existing user.
     *//*
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String id, ModelMap model) {
  
       User user = userService.findUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "welcome";
    }*/
     
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(User user, BindingResult result,
            ModelMap model, @PathVariable String id) {
    	if(user.getId() != null && !user.getId().trim().equals("")) {
			userService.edit(user);
		} else {
			userService.add(user);
		}
        return "welcome";
    }
    
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/save-user-{id}" }, method = RequestMethod.POST)
    public String saveUser(User user, 
            ModelMap model, @PathVariable String id) {
    	if(user.getId() != null && !user.getId().trim().equals("")) {
			userService.edit(user);
		} else {
			userService.add(user);
		}
        return "welcome";
    }
    
    /**
     * This method will delete an user by it's ID value.
     */
    @RequestMapping(value = { "/delete-user-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
    
   	userService.delete(id);
        return "welcome";
    }
	

	// Displaying the initial users list.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPersons(Model model) {
		log.debug("Request to fetch all users from the mongo database");
		List<User> user_list = userService.getAll();		
		model.addAttribute("users", user_list);		
		return "welcome";
	}

	// Opening the add new user form page.
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		log.debug("Request to open the new user form page");
		model.addAttribute("userAttr", new User());
		return "welcome";
	}

	// Opening the edit user form page.
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
		log.debug("Request to open the edit user form page");	
		model.addAttribute("userAttr", userService.findUserId(id));		
		return "welcome";
	}

	// Deleting the specified user.
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true) String id, Model model) {
		userService.delete(id);
		return "redirect:list";
	}

	// Adding a new user or updating an existing user.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("userAttr") User user) {
		if(user.getId() != null && !user.getId().trim().equals("")) {
			userService.edit(user);
		} else {
			userService.add(user);
		}
		return "redirect:list";
	}
}