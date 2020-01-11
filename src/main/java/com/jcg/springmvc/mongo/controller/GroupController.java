package com.jcg.springmvc.mongo.controller;

import com.jcg.springmvc.mongo.models.Group;
import com.jcg.springmvc.mongo.models.User;
import com.jcg.springmvc.mongo.services.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/group")
public class GroupController {

    private static Logger log = Logger.getLogger(GroupController.class);

    @Resource(name="groupService")
    private GroupService groupService;

    // Displaying the initial groups list.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getGroups(Model model) {
        log.debug("Request to fetch all groups from the mongo database");
        List<Group> group_list = groupService.getAll();
        model.addAttribute("groups", group_list);
        return "groupsList";
    }

    // Opening the add new group form page.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGroup(Model model) {
        log.debug("Request to open the new group form page");
        model.addAttribute("groupAttr", new Group());
        return "createGroup";
    }

    // Opening the edit group form page.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGroup(@RequestParam(value="id", required=true) String id, Model model) {
        log.debug("Request to open the edit group form page");
        model.addAttribute("groupAttr", groupService.findGroupId(id));
        return "createGroup";
    }

    // Deleting the specified group.
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        groupService.delete(id);
        return "redirect:list";
    }

    // Adding a new group or updating an existing group.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("groupAttr") Group group) {
        if(group.getId() != null && !group.getId().trim().equals("")) {
            groupService.edit(group);
        } else {
            groupService.add(group);
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@RequestParam(value="id", required=true) String id, Model model) {
        groupService.join(id);
        return "redirect:list";
    }


}
