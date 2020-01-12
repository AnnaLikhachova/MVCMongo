package com.jcg.springmvc.mongo.service;

import com.jcg.springmvc.mongo.factory.MongoFactory;
import com.jcg.springmvc.mongo.model.Chat;
import com.jcg.springmvc.mongo.model.Chat;
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

@Service("chatService")
@Transactional
public class ChatService {
	static String db_name = "mydb", db_collection = "chats";
	private static Logger log = Logger.getLogger(ChatService.class);

	public List<Chat> getAll() {
		List<Chat> chatList = new ArrayList<Chat>();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

		DBCursor cursor = coll.find();
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			Chat chat = new Chat();
			chat.setId(dbObject.get("id").toString());
			chat.setName(dbObject.get("name").toString());
			chat.setMessages((List<Message>) dbObject.get("messages"));
			chatList.add(chat);
		}
		log.debug("Total records fetched from the mongo database are= " + chatList.size());
		return chatList;
	}

	public Boolean add(Chat chat) {
		boolean output = false;
		Random ran = new Random();
		log.debug("Adding a new chat to the mongo database; Entered Chat_name is= " + chat.getName());
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			BasicDBObject doc = new BasicDBObject();
			doc.put("id", String.valueOf(ran.nextInt(100)));
			doc.put("name", chat.getName());
			doc.put("messages", chat.getMessages());

			// Save a new chat to the mongo collection.
			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new chat to the mongo database", e);
		}
		return output;
	}

	public Boolean edit(Chat chat) {
		boolean output = false;
		log.debug("Updating the existing chat in the mongo database; Entered Chat_id is= " + chat.getId());
		try {
			BasicDBObject existing = (BasicDBObject) getDBObject(chat.getId());

			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

			BasicDBObject edited = new BasicDBObject();
			edited.put("id", chat.getId());
			edited.put("name", chat.getName());
			edited.put("messages", chat.getMessages());

			coll.update(existing, edited);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error has occurred while updating an existing chat to the mongo database", e);
		}
		return output;
	}

	public Boolean delete(String id) {
		boolean output = false;
		log.debug("Deleting an existing Chat from the mongo database; Entered Chat_id is= " + id);
		try {
			BasicDBObject item = (BasicDBObject) getDBObject(id);
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
			coll.remove(item);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while deleting an existing Chat from the mongo database", e);
		}
		return output;
	}

	private DBObject getDBObject(String id) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBObject where_query = new BasicDBObject();
		where_query.put("id", id);
		return coll.findOne(where_query);
	}

	public Chat findChatId(String id) {
		Chat u = new Chat();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBObject where_query = new BasicDBObject();
		where_query.put("id", id);

		DBObject dbo = coll.findOne(where_query);
		u.setId(dbo.get("id").toString());
		u.setName(dbo.get("name").toString());
		u.setMessages((List<Message>) dbo.get("messages"));
		return u;
	}
}
