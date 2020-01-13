package com.jcg.springmvc.mongo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcg.springmvc.mongo.model.Message;
import com.jcg.springmvc.mongo.model.User;
import com.jcg.springmvc.mongo.service.ChatService;
import com.jcg.springmvc.mongo.service.MessageService;
import com.jcg.springmvc.mongo.service.UserService;

@Controller
public class ChatController {

private static Logger log = Logger.getLogger(ChatController.class);
	
	@Resource(name="chatService")
    private ChatService chatService;
	
	@Resource(name="messageService")
    private MessageService messageService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value = { "/createMessage" }, method = RequestMethod.GET)
	public String showForm(ModelMap model) {		
		Message message = new Message();
		model.addAttribute("message", message);
		return "createMessage";
	}
	
	@RequestMapping(value = { "/createMessage" }, method = RequestMethod.POST)
	public String saveGroup(ModelMap model, @ModelAttribute("message")  Message message, 
			@RequestParam(value = "reciever", defaultValue = "") String reciever, HttpSession session) {	
		User user = (User) session.getAttribute("loggedUser");
		User recieverUser = null;
		if(reciever != null) {
		recieverUser = userService.findUserByEmail(reciever);
		}
		if(recieverUser != null) {
			message.setChatId(recieverUser.getName());
		}else {
			return "createMessage";
		}	
		if(user != null) {
		message.setSender(user);	
		}
		if(message.getId() != null && !message.getId().trim().equals("")) {
			messageService.editMessage(message);
		} else {
			messageService.addMessage(message);
			log.debug(message);
		}
		return "redirect:/chat";
	}

    // Displaying the initial chats list.
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String getGroups(Model model) {
        log.debug("Request to fetch all messages from the mongo database");
        List<Message> messageList = messageService.getAllMessages();
        model.addAttribute("messages", messageList);
        return "chat";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating message in database. It also validates the chat input
     */
    @RequestMapping(value = { "/edit-message-{id}" }, method = RequestMethod.GET)
    public String updateChatr(
            ModelMap model, @PathVariable String id) {
    	Message message = messageService.findMessageId(id);
    		model.addAttribute("message", message);
        model.addAttribute("edit", true);
        return "createMessage";
    }
    
	 /**
     * This method will be called on form submission, handling POST request for
     * updating message in database. It also validates the message input
     */
    @RequestMapping(value = { "/edit-message-{id}" }, method = RequestMethod.POST)
    public String updateChat(Message message, 
            ModelMap model, @PathVariable String id) {
 
    	if(message.getId() != null && !message.getId().trim().equals("")) {
    		messageService.editMessage(message);
		} else {
			messageService.addMessage(message);
		}
        return "chat";
    }
    
    /**
     * This method will delete an user by it's ID value.
     */
    @RequestMapping(value = { "/delete-message-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
    
    	messageService.deleteMessage(id);
        return "redirect:/groupsList";
    }
}
