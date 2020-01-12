package com.jcg.springmvc.mongo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postService")
@Transactional
public class PostService {
	static String db_name = "mydb", db_collection = "posts";
	private static Logger log = Logger.getLogger(PostService.class);

}
