package com.jcg.springmvc.mongo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleService {
	static String db_name = "mydb", db_collection = "roles";
	private static Logger log = Logger.getLogger(RoleService.class);

}
