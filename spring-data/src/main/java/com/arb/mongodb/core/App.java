package com.arb.mongodb.core;
 
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.arb.mongodb.model.User;
//import org.springframework.context.support.GenericXmlApplicationContext;
 
public class App {
 
    public static void main(String[] args) {
 
	// For XML
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	MongoOperations mongoOperation = (MongoOperations) context.getBean("mongoTemplate");

	User user = new User("mkyong", "password123");
 
	// save
	mongoOperation.save(user);
 
	// now user object got the created id.
	System.out.println("1. user : " + user);
 
	// query to search user
	Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
 
	// find the saved user again.
	User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
	System.out.println("2. find - savedUser : " + savedUser);
 
	// update password
	mongoOperation.updateFirst(searchUserQuery, 
                         Update.update("password", "new password"),User.class);
 
	// find the updated user object
	User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);
 
	System.out.println("3. updatedUser : " + updatedUser);
 
	// delete
	mongoOperation.remove(searchUserQuery, User.class);
 
	// List, it should be empty now.
	List<User> listUser = mongoOperation.findAll(User.class);
	System.out.println("4. Number of user = " + listUser.size());
 
    }
 
}