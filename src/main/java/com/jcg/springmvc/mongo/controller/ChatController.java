package com.jcg.springmvc.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {

	@RequestMapping(value = { "/chat" }, method = RequestMethod.GET)
	public String post(ModelMap model) {
		return "/chat";
	}
	
	@RequestMapping(value = { "createMessage" }, method = RequestMethod.GET)
	public String createPost(ModelMap model) {
		return "/createMessage";
	}
}
