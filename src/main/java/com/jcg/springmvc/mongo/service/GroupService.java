package com.jcg.springmvc.mongo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
@Transactional
public class GroupService {
	static String db_name = "mydb", db_collection = "groups";
	private static Logger log = Logger.getLogger(GroupService.class);

}
