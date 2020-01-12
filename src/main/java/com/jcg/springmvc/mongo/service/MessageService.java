package com.jcg.springmvc.mongo.service;

import com.jcg.springmvc.mongo.factory.MongoFactory;
import com.jcg.springmvc.mongo.model.Message;
import com.jcg.springmvc.mongo.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("messageService")
@Transactional
public class MessageService {
	static String db_name = "mydb", db_collection = "messages";
	private static Logger log = Logger.getLogger(MessageService.class);

	public List<Message> getAll() {
		List<Message> messageList = new ArrayList<Message>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching cursor object for iterating on the database records.
		DBCursor cursor = coll.find();
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Message message = new Message();
			Object o = dbObject.get("id");
			message.setId(dbObject.get("id").toString());
			Object id = dbObject.get("chatId");

			if(id == null) {
				message.setMessage("");
			} else {
				message.setMessage(dbObject.get("chatId").toString());
			}

			Object msg = dbObject.get("message");
			if(msg == null) {
				message.setMessage("");
			} else {
				message.setMessage(dbObject.get("message").toString());
			}

			messageList.add(message);
		}
		log.debug("Total records fetched from the mongo database are= " + messageList.size());
		return messageList;
	}

	// Add a new message to the mongo database.
	public Boolean add(Message message) {
		boolean output = false;
		Random ran = new Random();
		log.debug("Adding a new message to the mongo database; Entered Message_name is= " + message.getMessage());
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and add the new message details to this object.
			BasicDBObject doc = new BasicDBObject();
			doc.put("id", String.valueOf(ran.nextInt(100)));
			doc.put("message", message.getMessage());
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

	// Update the selected message in the mongo database.
	public Boolean edit(Message message) {
		boolean output = false;
		log.debug("Updating the existing message in the mongo database; Entered Message_id is= " + message.getId());
		try {
			// Fetching the message details.
			BasicDBObject existing = (BasicDBObject) getDBObject(message.getId());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Create a new object and assign the updated details.
			BasicDBObject edited = new BasicDBObject();
			edited.put("id", message.getId());
			edited.put("message", message.getMessage());

			// Update the existing message to the mongo database.
			coll.update(existing, edited);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error has occurred while updating an existing message to the mongo database", e);
		}
		return output;
	}

	// Delete a Message from the mongo database.
	public Boolean delete(String id) {
		boolean output = false;
		log.debug("Deleting an existing Message from the mongo database; Entered Message_id is= " + id);
		try {
			// Fetching the required Message from the mongo database.
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			// Deleting the selected Message from the mongo database.
			coll.remove(item);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while deleting an existing Message from the mongo database", e);
		}
		return output;
	}

	// Fetching a particular record from the mongo database.
	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();

		// Put the selected Message_id to search.
		where_query.put("id", id);
		return coll.findOne(where_query);
	}

	// Fetching a single Message details from the mongo database.
	public Message findMessageId(String id) {
		Message message = new Message();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		// Fetching the record object from the mongo database.
		DBObject where_query = new BasicDBObject();
		where_query.put("id", id);

		DBObject dbo = coll.findOne(where_query);
		message.setId(dbo.get("id").toString());
		message.setMessage(dbo.get("message").toString());

		// Return Message object.
		return message;
	}
}
