package com.jcg.springmvc.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcg.springmvc.mongo.factory.MongoFactory;
import com.jcg.springmvc.mongo.model.Message;
import com.jcg.springmvc.mongo.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service("messageService")
@Transactional
public class MessageService {
	static String db_name = "mydb", db_collection = "messages";
	private static Logger log = Logger.getLogger(MessageService.class);
	
	// Fetch all messages from the mongo database.
		public List<Message> getAllMessages() {
			List<Message> messageList = new ArrayList<Message>();
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching cursor object for iterating on the database records.
			DBCursor cursor = coll.find();
			while (cursor.hasNext()) {
				DBObject dbObject = cursor.next();

				Message message = new Message();
				message.setId(dbObject.get("id").toString());
				message.setMessage(dbObject.get("message").toString());
				message.setReciever((User) dbObject.get("reciever"));
				message.setSender((User) dbObject.get("sender"));
				message.setTimestamp((Date) dbObject.get("timestamp"));
				message.setChatId(dbObject.get("chatId").toString());
				// Adding the message details to the list.
				messageList.add(message);
			}
			log.debug("Total records fetched from the mongo database are= " + messageList.size());
			return messageList;
		}

		// Add a new message to the mongo database.
		public Boolean addMessage(Message message) {
			boolean output = false;
			Random ran = new Random();
			log.debug("Adding a new message to the mongo database; Entered message_id is= " + message.getId());
			try {			
				DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

				// Create a new object and add the new message details to this object.
				BasicDBObject doc = new BasicDBObject();
				doc.put("id", String.valueOf(ran.nextInt(100))); 
				doc.put("message", message.getMessage());			
				doc.put("reciever", message.getReciever());
				doc.put("sender", message.getSender());
				doc.put("timestamp", message.getTimestamp());
				doc.put("chatId", message.getChatId());
				// Save a new message to the mongo collection.
				coll.insert(doc);
				output = true;
				
			} catch (Exception e) {
				output = false;
				log.error("An error occurred while saving a new message to the mongo database", e);			
			}
			return output;
		}

		// Update the selected user in the mongo database.
		public Boolean editMessage(Message message) {
			boolean output = false;
			message.setTimestamp(new Date());
			log.debug("Updating the existing sender in the mongo database; Entered message_id is= " + message.getId());
			try {
				// Fetching the user details.
				BasicDBObject existing = (BasicDBObject) getDBObject(message.getId());

				DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

				// Create a new object and assign the updated details.
				BasicDBObject edited = new BasicDBObject();
				edited.put("id", message.getId()); 
				edited.put("message", message.getMessage());
				edited.put("reciever", message.getReciever());
				edited.put("sender", message.getSender());
				edited.put("timestamp", message.getTimestamp());
				edited.put("chatId", message.getChatId());
				// Update the existing message to the mongo database.
				coll.update(existing, edited);
				output = true;
			} catch (Exception e) {
				output = false;
				log.error("An error has occurred while updating an existing message to the mongo database", e);			
			}
			return output;
		}

		// Delete a user from the mongo database.
		public Boolean deleteMessage(String id) {
			boolean output = false;
			log.debug("Deleting an existing message from the mongo database; Entered message_id is= " + id);
			try {
				// Fetching the required user from the mongo database.
				BasicDBObject item = (BasicDBObject) getDBObject(id);

				DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

				// Deleting the selected user from the mongo database.
				coll.remove(item);
				output = true;			
			} catch (Exception e) {
				output = false;
				log.error("An error occurred while deleting an existing message from the mongo database", e);			
			}	
			return output;
		}

		// Fetching a particular record from the mongo database.
		private DBObject getDBObject(String id) {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching the record object from the mongo database.
			DBObject where_query = new BasicDBObject();

			// Put the selected sender_id to search.
			where_query.put("id", id);
			return coll.findOne(where_query);
		}

		// Fetching a single message details from the mongo database.
		public Message findMessageId(String id) {
			Message u = new Message();
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching the record object from the mongo database.
			DBObject where_query = new BasicDBObject();
			where_query.put("id", id);

			DBObject dbo = coll.findOne(where_query);		
			u.setId(dbo.get("id").toString());
			u.setMessage(dbo.get("message").toString());
			u.setReciever((User) dbo.get("reciever"));
			u.setSender((User) dbo.get("sender"));
			u.setTimestamp((Date) dbo.get("timestamp"));
			u.setChatId(dbo.get("chatId").toString());
			// Return user object.
			return u;
		}
		

		public Message findMessageBySender(User sender) {
			
			Message u = new Message();
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Fetching the record object from the mongo database.
			DBObject where_query = new BasicDBObject();
			where_query.put("sender", sender);

			DBObject dbo = coll.findOne(where_query);		
			u.setId(dbo.get("id").toString());
			u.setMessage(dbo.get("message").toString());
			u.setReciever((User) dbo.get("reciever"));
			u.setSender((User) dbo.get("sender"));
			u.setTimestamp((Date) dbo.get("timestamp"));
			// Return message object.
			return u;
		}

}
