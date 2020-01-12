package com.jcg.springmvc.mongo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageService")
@Transactional
public class MessageService {
	static String db_name = "mydb", db_collection = "messages";
	private static Logger log = Logger.getLogger(MessageService.class);

}
