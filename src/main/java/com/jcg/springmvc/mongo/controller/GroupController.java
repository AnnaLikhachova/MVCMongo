package com.jcg.springmvc.mongo.controller;

import org.springframework.stereotype.Controller;
import com.jcg.springmvc.mongo.model.Group;
import com.jcg.springmvc.mongo.model.User;
import com.jcg.springmvc.mongo.service.GroupService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@Controller
public class GroupController {

	private static Logger log = Logger.getLogger(GroupController.class);
	
	@Resource(name="groupService")
    private GroupService groupService;
	
	@RequestMapping(value = { "/createGroup" }, method = RequestMethod.GET)
	public String showForm(ModelMap model) {		
		Group group = new Group();
		model.addAttribute("group", group);
		return "createGroup";
	}
	
	@RequestMapping(value = { "/createGroup" }, method = RequestMethod.POST)
	public String saveGroup(ModelMap model, @ModelAttribute("group")  Group group,  HttpSession session) {	
		List<User> users = new ArrayList<>();
		User user = (User) session.getAttribute("loggedUser");
		users.add(user);
		if(group.getId() != null && !group.getId().trim().equals("")) {
			groupService.edit(group);
		} else {
			group.setUsers(users);
			groupService.add(group);
		}
		return "redirect:/groupsList";
	}

    // Displaying the initial groups list.
    @RequestMapping(value = "/groupsList", method = RequestMethod.GET)
    public String getGroups(Model model) {
        log.debug("Request to fetch all groups from the mongo database");
        List<Group> groupList = groupService.getAll();
        model.addAttribute("groups", groupList);
        return "groupsList";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-group-{id}" }, method = RequestMethod.GET)
    public String updateUser(
            ModelMap model, @PathVariable String id) {
    Group group = groupService.findGroupId(id);
    		model.addAttribute("group", group);
        model.addAttribute("edit", true);
        return "createGroup";
    }
    
	 /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-group-{id}" }, method = RequestMethod.POST)
    public String updateUser(Group group, 
            ModelMap model, @PathVariable String id) {
 
    	if(group.getId() != null && !group.getId().trim().equals("")) {
    		groupService.edit(group);
		} else {
			groupService.add(group);
		}
        return "groupList";
    }
    
    /**
     * This method will delete an user by it's ID value.
     */
    @RequestMapping(value = { "/delete-group-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
    
   	groupService.delete(id);
        return "redirect:/groupsList";
    }
    
}
