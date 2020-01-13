package com.jcg.springmvc.mongo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("chatService")
@Transactional
public class ChatService {
	static String db_name = "mydb", db_collection = "chats";
	private static Logger log = Logger.getLogger(ChatService.class);
}
