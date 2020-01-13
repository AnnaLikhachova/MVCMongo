package com.jcg.springmvc.mongo.controller;

import java.util.Date;
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

import com.jcg.springmvc.mongo.model.Post;
import com.jcg.springmvc.mongo.model.User;
import com.jcg.springmvc.mongo.service.PostService;

@Controller
public class PostController {
	
	private static Logger log = Logger.getLogger(PostController.class);
	
	@Resource(name="postService")
	private PostService postService;
		
	@RequestMapping(value = { "/createPost" }, method = RequestMethod.GET)
	public String showPost(ModelMap model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return "/createPost";
	}
	
	@RequestMapping(value = { "/createPost" }, method = RequestMethod.POST)
	public String savePost(ModelMap model, @ModelAttribute("post")  Post post,  HttpSession session) {	
		User user = (User) session.getAttribute("loggedUser");
		if(post.getId() != null && !post.getId().trim().equals("")) {
			postService.edit(post);
		} else {
			post.setSender(user);
			post.setTimestamp(new Date());
			postService.add(post);
		}
		return "redirect:/post";
	}
	
	// Displaying the initial posts list.
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String getGroups(Model model) {
        log.debug("Request to fetch all posts from the mongo database");
        List<Post> postList = postService.getAll();
        model.addAttribute("posts", postList);
        return "post";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating post in database. It also validates the post input
     */
    @RequestMapping(value = { "/edit-post-{id}" }, method = RequestMethod.GET)
    public String updateUser(
            ModelMap model, @PathVariable String id) {
    Post post = postService.findPostId(id);
    		model.addAttribute("post", post);
        model.addAttribute("edit", true);
        return "createPost";
    }
    
	 /**
     * This method will be called on form submission, handling POST request for
     * updating post in database. It also validates the post input
     */
    @RequestMapping(value = { "/edit-post-{id}" }, method = RequestMethod.POST)
    public String updateUser(Post post, 
            ModelMap model, @PathVariable String id) {
    	post.setTimestamp(new Date());
    	if(post.getId() != null && !post.getId().trim().equals("")) {
    		postService.edit(post);
		} else {
			postService.add(post);
		}
        return "post";
    }
    
    /**
     * This method will delete an post by it's ID value.
     */
    @RequestMapping(value = { "/delete-post-{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
    
    	postService.delete(id);
        return "redirect:/post";
    }

}
