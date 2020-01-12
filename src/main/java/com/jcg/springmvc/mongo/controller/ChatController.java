package com.jcg.springmvc.mongo.controller;

import com.jcg.springmvc.mongo.model.Chat;
import com.jcg.springmvc.mongo.model.Message;
import com.jcg.springmvc.mongo.service.ChatService;
import com.jcg.springmvc.mongo.service.MessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ChatController {

    private static Logger log = Logger.getLogger(ChatController.class);

    @Resource(name="chatService")
    private ChatService chatService;

    @Resource(name="messageService")
    private MessageService messageService;

    @RequestMapping(value = { "/createChat" }, method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        Chat chat = new Chat();
        model.addAttribute("chat", chat);
        return "createChat";
    }

    @RequestMapping(value = { "/createChat" }, method = RequestMethod.POST)
    public String saveUser(ModelMap model, @ModelAttribute("chat")  Chat chat) {
        if(chat.getId() != null && !chat.getId().trim().equals("")) {
            chatService.edit(chat);
        } else {
            chatService.add(chat);
        }
        return "redirect:/chatList";
    }

    // Displaying the initial chats list.
    @RequestMapping(value = "/chatList", method = RequestMethod.GET)
    public String getChats(Model model) {
        log.debug("Request to fetch all chats from the mongo database");
        List<Chat> chatList = chatService.getAll();
        model.addAttribute("chats", chatList);
        return "chatList";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-chat-{id}" }, method = RequestMethod.GET)
    public String updateUser(
            ModelMap model, @PathVariable String id) {
        Chat chat = chatService.findChatId(id);
        model.addAttribute("chat", chat);
        model.addAttribute("edit", true);
        return "createChat";
    }

    @RequestMapping(value = { "/goto-chat-{id}" }, method = RequestMethod.GET)
    public String goToChat(
            ModelMap model,
            @PathVariable String id,
            @ModelAttribute("messageAttr")  Message message) {
        Chat chat = chatService.findChatId(id);
        List<Message> messages = messageService.getAll();
        model.addAttribute("chat", chat);
        model.addAttribute("messages", messages);
        return "chat";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-chat-{id}" }, method = RequestMethod.POST)
    public String updateUser(Chat chat,
                             ModelMap model, @PathVariable String id) {

        if(chat.getId() != null && !chat.getId().trim().equals("")) {
            chatService.edit(chat);
        } else {
            chatService.add(chat);
        }
        return "chatList";
    }

    // Opening the add new chat form page.
    @RequestMapping(value = "/addChat", method = RequestMethod.GET)
    public String addChat(Model model) {
        log.debug("Request to open the new chat form page");
        model.addAttribute("chatAttr", new Chat());
        return "createChat";
    }

    // Opening the edit chat form page.
    @RequestMapping(value = "/editChat", method = RequestMethod.GET)
    public String editChat(@RequestParam(value="id", required=true) String id, Model model) {
        log.debug("Request to open the edit chat form page");
        model.addAttribute("chatAttr", chatService.findChatId(id));
        return "createChat";
    }

    // Deleting the specified chat.
    @RequestMapping(value = "/deleteChat", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        chatService.delete(id);
        return "redirect:list";
    }

    // Adding a new chat or updating an existing chat.
    @RequestMapping(value = "/saveChat", method = RequestMethod.POST)
    public String save(@ModelAttribute("chatAttr") Chat chat) {
        if(chat.getId() != null && !chat.getId().trim().equals("")) {
            chatService.edit(chat);
        } else {
            chatService.add(chat);
        }
        return "redirect:chatList";
    }
    
}
