package com.jcg.springmvc.mongo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jcg.springmvc.mongo.model.Group;
import com.jcg.springmvc.mongo.model.User;
import com.mongodb.*;
import org.bson.BSONObject;
import com.jcg.springmvc.mongo.factory.MongoFactory;

@Service("groupService")
@Transactional
public class GroupService {
	static String db_name = "mydb", db_collection = "groups";
	private static Logger log = Logger.getLogger(GroupService.class);
	
	public List<Group> getAll() {
        List<Group> GroupList = new ArrayList<Group>();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

        // Fetching cursor object for iterating on the database records.
        DBCursor cursor = coll.find();
        while(cursor.hasNext()) {
            DBObject dbObject = cursor.next();

            Group Group = new Group();
            Group.setId(dbObject.get("id").toString());
            Group.setName(dbObject.get("name").toString());
            Group.setDescription(dbObject.get("description").toString());            
            Group.setUsers((List<User>) dbObject.get("users"));        
            // Adding the Group details to the list.
            GroupList.add(Group);
        }
        log.debug("Total records fetched from the mongo database are= " + GroupList.size());
        return GroupList;
    }

    // Add a new Group to the mongo database.
    public Boolean add(Group Group) {
        boolean output = false;
        Random ran = new Random();
        log.debug("Adding a new Group to the mongo database; Entered Group_name is= " + Group.getName());
        try {
            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

            // Create a new object and add the new Group details to this object.
            BasicDBObject doc = new BasicDBObject();
            doc.put("id", String.valueOf(ran.nextInt(100)));
            doc.put("name", Group.getName());
            doc.put("description", Group.getDescription());
            doc.put("users", Group.getUsers());

            // Save a new Group to the mongo collection.
            coll.insert(doc);
            output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error occurred while saving a new Group to the mongo database", e);
        }
        return output;
    }

    // Update the selected Group in the mongo database.
    public Boolean edit(Group Group) {
        boolean output = false;
        log.debug("Updating the existing Group in the mongo database; Entered Group_id is= " + Group.getId());
        try {
            // Fetching the Group details.
            BasicDBObject existing = (BasicDBObject) getDBObject(Group.getId());

            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

            // Create a new object and assign the updated details.
            BasicDBObject edited = new BasicDBObject();
            edited.put("id", Group.getId());
            edited.put("name", Group.getName());
            edited.put("description", Group.getDescription());
            edited.put("users", Group.getUsers());

            // Update the existing Group to the mongo database.
            coll.update(existing, edited);
            output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error has occurred while updating an existing Group to the mongo database", e);
        }
        return output;
    }

    // Delete a Group from the mongo database.
    public Boolean delete(String id) {
        boolean output = false;
        log.debug("Deleting an existing Group from the mongo database; Entered Group_id is= " + id);
        try {
            // Fetching the required Group from the mongo database.
            BasicDBObject item = (BasicDBObject) getDBObject(id);

            DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

            // Deleting the selected Group from the mongo database.
            coll.remove(item);
            output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error occurred while deleting an existing Group from the mongo database", e);
        }
        return output;
    }

    // Fetching a particular record from the mongo database.
    private DBObject getDBObject(String id) {
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();

        // Put the selected Group_id to search.
        where_query.put("id", id);
        return coll.findOne(where_query);
    }

    // Fetching a single Group details from the mongo database.
    public Group findGroupId(String id) {
        Group u = new Group();
        DBCollection coll = MongoFactory.getCollection(db_name, db_collection);

        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();
        where_query.put("id", id);

        DBObject dbo = coll.findOne(where_query);
        u.setId(dbo.get("id").toString());
        u.setName(dbo.get("name").toString());
        u.setDescription(dbo.get("description").toString());
        u.setUsers((List<User>) dbo.get("users"));

        // Return Group object.
        return u;
    }

}
