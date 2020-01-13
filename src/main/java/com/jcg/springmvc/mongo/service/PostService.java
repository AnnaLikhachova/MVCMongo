package com.jcg.springmvc.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcg.springmvc.mongo.factory.MongoFactory;
import com.jcg.springmvc.mongo.model.Post;
import com.jcg.springmvc.mongo.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("postService")
@Transactional
public class PostService {
	static String db_name = "mydb", db_collection = "posts";
	private static Logger log = Logger.getLogger(PostService.class);

	// Fetch all posts from the mongo database.
		public List<Post> getAll() {
			List<Post> postList = new ArrayList<Post>();
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Post post = new Post();
				post.setId(dbObject.get("id").toString());
				post.setMessage(dbObject.get("message").toString());
				post.setTimestamp((Date) dbObject.get("timestamp"));
				post.setSender((User) dbObject.get("sender"));
				// Adding the post details to the list.
				postList.add(post);
			}
			log.debug("Total records fetched from the mongo database are= " + postList.size());
			return postList;
		}

		// Add a new post to the mongo database.
		public Boolean add(Post post) {
			boolean output = false;
			Random ran = new Random();
			log.debug("Adding a new post to the mongo database; Entered post_id is= " + post.getId());
			try {			
				DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

				// Create a new object and add the new post details to this object.
				BasicDBObject doc = new BasicDBObject();
				doc.put("id", String.valueOf(ran.nextInt(100))); 
				doc.put("message", post.getMessage());			
				doc.put("timestamp", post.getTimestamp());
				doc.put("sender", post.getSender());
				// Save a new post to the mongo collection.
				coll.insert(doc);
				output = true;
			} catch (Exception e) {
				output = false;
				log.error("An error occurred while saving a new post to the mongo database", e);			
			}
			return output;
		}

		// Update the selected post in the mongo database.
		public Boolean edit(Post post) {
			boolean output = false;
			post.setTimestamp(new Date());
			log.debug("Updating the existing post in the mongo database; Entered post_id is= " + post.getId());
			try {
				// Fetching the post details.
				BasicDBObject existing = (BasicDBObject) getDBObject(post.getId());

				DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

				// Create a new object and assign the updated details.
				BasicDBObject edited = new BasicDBObject();
				edited.put("id", post.getId()); 
				edited.put("message", post.getMessage());			
				edited.put("timestamp", post.getTimestamp());
				edited.put("sender", post.getSender());
				// Update the existing post to the mongo database.
				coll.update(existing, edited);
				output = true;
			} catch (Exception e) {
				output = false;
				log.error("An error has occurred while updating an existing post to the mongo database", e);			
			}
			return output;
		}

		// Delete a post from the mongo database.
		public Boolean delete(String id) {
			boolean output = false;
			log.debug("Deleting an existing post from the mongo database; Entered post_id is= " + id);
			try {
				// Fetching the required user from the mongo database.
				BasicDBObject item = (BasicDBObject) getDBObject(id);

				DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

				// Deleting the selected user from the mongo database.
				coll.remove(item);
				output = true;			
			} catch (Exception e) {
				output = false;
				log.error("An error occurred while deleting an existing post from the mongo database", e);			
			}	
			return output;
		}

		// Fetching a particular record from the mongo database.
		private DBObject getDBObject(String id) {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching the record object from the mongo database.
			DBObject where_query = new BasicDBObject();

			// Put the selected user_id to search.
			where_query.put("id", id);
			return coll.findOne(where_query);
		}

		// Fetching a single user details from the mongo database.
		public Post findPostId(String id) {
			Post u = new Post();
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching the record object from the mongo database.
			DBObject where_query = new BasicDBObject();
			where_query.put("id", id);

			DBObject dbo = coll.findOne(where_query);		
			u.setId(dbo.get("id").toString());
			u.setMessage(dbo.get("message").toString());
			u.setTimestamp((Date) dbo.get("timestamp"));
			u.setSender((User) dbo.get("sender"));
			// Return user object.
			return u;
		}
		

		public Post findPostByUser(User user) {
			
			Post u = new Post();
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching the record object from the mongo database.
			DBObject where_query = new BasicDBObject();
			where_query.put("sender", user);

			DBObject dbo = coll.findOne(where_query);		
			u.setId(dbo.get("id").toString());
			u.setMessage(dbo.get("message").toString());
			u.setTimestamp((Date) dbo.get("timestamp"));
			u.setSender((User) dbo.get("sender"));
			// Return user object.
			return u;
		}
}
