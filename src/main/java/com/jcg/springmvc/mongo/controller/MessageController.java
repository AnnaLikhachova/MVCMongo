package com.jcg.springmvc.mongo.controller;

import com.jcg.springmvc.mongo.model.Message;
import com.jcg.springmvc.mongo.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@Controller
public class MessageController {

	private static Logger log = Logger.getLogger(MessageController.class);
	
	@Resource(name="messageService")
    private MessageService messageService;
	
	@RequestMapping(value = { "/createMessage" }, method = RequestMethod.GET)
	public String showForm(ModelMap model) {		
		 Message message = new Message();
		model.addAttribute("message", message);
		return "createMessage";
	}
	
	@RequestMapping(value = { "/createMessage" }, method = RequestMethod.POST)
	public String saveMessage(ModelMap model,
                              @ModelAttribute("messageAttr")  Message message,
                              HttpServletRequest request) {
		if(message.getId() != null && !message.getId().trim().equals("")) {
			messageService.edit(message);
		} else {
            Random ran = new Random();
            message.setId(String.valueOf(ran.nextInt(100)));
			messageService.add(message);
		}
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
	}

    // Displaying the initial messages list.
    @RequestMapping(value = "/messagesList", method = RequestMethod.GET)
    public String getMessages(Model model) {
        log.debug("Request to fetch all messages from the mongo database");
        List<Message> messageList = messageService.getAll();
        model.addAttribute("messages", messageList);
        return "messagesList";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-message-{id}" }, method = RequestMethod.GET)
    public String updateMessage(
            ModelMap model, @PathVariable String id) {
    Message message = messageService.findMessageId(id);
    		model.addAttribute("message", message);
        model.addAttribute("edit", true);
        return "createMessage";
    }
    
	 /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-message-{id}" }, method = RequestMethod.POST)
    public String updateMessage(Message message, 
            ModelMap model, @PathVariable String id) {
 
    	if(message.getId() != null && !message.getId().trim().equals("")) {
    		messageService.edit(message);
		} else {
			messageService.add(message);
		}
        return "messageList";
    }
    
    /**
     * This method will delete an user by it's ID value.
     */
    @RequestMapping(value = { "/delete-message-{id}" }, method = RequestMethod.GET)
    public String deleteMessage(@PathVariable String id, ModelMap model) {
    
   	messageService.delete(id);
        return "redirect:/messageList";
    }
    

    // Opening the add new message form page.
    @RequestMapping(value = "/addMessage", method = RequestMethod.GET)
    public String addMessage(Model model) {
        log.debug("Request to open the new message form page");
        model.addAttribute("messageAttr", new Message());
        return "createMessage";
    }

    // Opening the edit message form page.
    @RequestMapping(value = "/editMessage", method = RequestMethod.GET)
    public String editMessage(@RequestParam(value="id", required=true) String id, Model model) {
        log.debug("Request to open the edit message form page");
        model.addAttribute("messageAttr", messageService.findMessageId(id));
        return "createMessage";
    }

    // Deleting the specified message.
    @RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
    public String deleteMessage(@RequestParam(value="id", required=true) String id, Model model) {
        messageService.delete(id);
        return "redirect:list";
    }

    // Adding a new message or updating an existing message.
    @RequestMapping(value = "/saveMessage", method = RequestMethod.POST)
    public String saveMessage(@ModelAttribute("messageAttr") Message message) {
        if(message.getId() != null && !message.getId().trim().equals("")) {
            messageService.edit(message);
        } else {
            messageService.add(message);
        }
        return "redirect:list";
    }
}
