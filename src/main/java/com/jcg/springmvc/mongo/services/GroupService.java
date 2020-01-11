package com.jcg.springmvc.mongo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jcg.springmvc.mongo.controller.UserController;
import com.jcg.springmvc.mongo.models.Group;
import com.jcg.springmvc.mongo.models.User;
import com.mongodb.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcg.springmvc.mongo.factory.MongoFactory;

@Service("groupService")
@Transactional
public class GroupService {

    static String db_name = "mydb", collection_name = "groups";
    private static Logger log = Logger.getLogger(GroupService.class);

    // Fetch all Groups from the mongo database.
    public List<Group> getAll() {
        List<Group> Group_list = new ArrayList<Group>();
        DBCollection coll = MongoFactory.getCollection(db_name, collection_name);

        // Fetching cursor object for iterating on the database records.
        DBCursor cursor = coll.find();
        while(cursor.hasNext()) {
            DBObject dbObject = cursor.next();

            Group Group = new Group();
            Group.setId(dbObject.get("id").toString());
            Group.setName(dbObject.get("name").toString());
            BasicDBList list = (BasicDBList) dbObject.get("members");
            User[] users = (User[]) list.toArray(new User[0]);
            Group.setMembers(users);
            // Adding the Group details to the list.
            Group_list.add(Group);
        }
        log.debug("Total records fetched from the mongo database are= " + Group_list.size());
        return Group_list;
    }

    // Add a new Group to the mongo database.
    public Boolean add(Group Group) {
        boolean output = false;
        Random ran = new Random();
        log.debug("Adding a new Group to the mongo database; Entered Group_name is= " + Group.getName());
        try {
            DBCollection coll = MongoFactory.getCollection(db_name, collection_name);

            // Create a new object and add the new Group details to this object.
            BasicDBObject doc = new BasicDBObject();
            doc.put("id", String.valueOf(ran.nextInt(100)));
            doc.put("name", Group.getName());
            doc.put("members", Group.getMembers());

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
            BasicDBObject existing = (BasicDBObject) getDBObject(Group.getId(), db_name, collection_name);

            DBCollection coll = MongoFactory.getCollection(db_name, collection_name);

            // Create a new object and assign the updated details.
            BasicDBObject edited = new BasicDBObject();
            edited.put("id", Group.getId());
            edited.put("name", Group.getName());
            edited.put("members", Group.getMembers());

            // Update the existing Group to the mongo database.
            coll.update(existing, edited);
            output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error has occurred while updating an existing Group to the mongo database", e);
        }
        return output;
    }

    public Boolean join(String id) {
        boolean output = false;
        log.debug("Updating the existing group in the mongo database; Entered Group_id is= " + id);
        try {
            // Fetching the group details.
            UserService userService = new UserService();
            BasicDBObject existingGroup = (BasicDBObject) getDBObject(id, db_name, collection_name);
            BasicDBObject existingUser = (BasicDBObject) getDBObject(id, UserService.db_name, UserService.collection_name);
            User user = userService.findFirstUser();
            Group group = this.findGroupId(id);
            DBCollection groupsColl = MongoFactory.getCollection(db_name, collection_name);
            DBCollection usersColl = MongoFactory.getCollection(UserService.db_name, UserService.collection_name);
            List<User> users = new ArrayList<>();
            List<Group> groups = new ArrayList<>();
            if(group.getMembers() != null && user.getGroups() != null) {
                users = Arrays.asList(group.getMembers());
                groups = Arrays.asList(user.getGroups());
            }

            if(!users.contains(user) && !groups.contains(group)) {
                user.addToGroup(group);
                group.addToGroup(user);
            } else {
                return output;
            }

            // Create a new object and assign the updated details.
            BasicDBObject editedGroup = new BasicDBObject();
            editedGroup.put("id", group.getId());
            editedGroup.put("name", group.getName());
            editedGroup.put("members", group.getMembers());

            BasicDBObject editedUser = new BasicDBObject();
            editedUser.put("id", user.getId());
            editedUser.put("name", user.getName());
            editedUser.put("groups", user.getGroups());

            // Update the existingGroup group to the mongo database.
            groupsColl.update(existingGroup, editedGroup);
            usersColl.update(existingUser, editedUser);
            output = true;
        } catch (Exception e) {
            output = false;
            log.error("An error has occurred while updating an existing group to the mongo database", e);
        }
        return output;
    }

    // Delete a Group from the mongo database.
    public Boolean delete(String id) {
        boolean output = false;
        log.debug("Deleting an existing Group from the mongo database; Entered Group_id is= " + id);
        try {
            // Fetching the required Group from the mongo database.
            BasicDBObject item = (BasicDBObject) getDBObject(id, db_name, collection_name);

            DBCollection coll = MongoFactory.getCollection(db_name, collection_name);

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
    private DBObject getDBObject(String id, String db_name, String collectionName) {
        DBCollection coll = MongoFactory.getCollection(db_name, collectionName);

        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();

        // Put the selected Group_id to search.
        where_query.put("id", id);
        return coll.findOne(where_query);
    }

    // Fetching a single Group details from the mongo database.
    public Group findGroupId(String id) {
        Group u = new Group();
        DBCollection coll = MongoFactory.getCollection(db_name, collection_name);

        // Fetching the record object from the mongo database.
        DBObject where_query = new BasicDBObject();
        where_query.put("id", id);

        DBObject dbo = coll.findOne(where_query);
        u.setId(dbo.get("id").toString());
        u.setName(dbo.get("name").toString());
        BasicDBList dbList = (BasicDBList) dbo.get("members");
        ArrayList<User> users = new ArrayList<>();
        dbList.forEach(o -> {
            System.out.println(o);
        });

        //u.setMembers((User[]) dbo.get("members"));

        // Return Group object.
        return u;
    }
}
