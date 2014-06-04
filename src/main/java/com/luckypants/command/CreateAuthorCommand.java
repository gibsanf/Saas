package com.luckypants.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CreateAuthorCommand {

	public String execute(Author myauthors) {
		ConnectionProvider myconn = new ConnectionProvider();
		DBCollection collection = myconn.getCollection("authors");

		ObjectMapper mymapper = new ObjectMapper();
		try {
			DBObject mydbObjects = (DBObject) JSON.parse(mymapper
					.writeValueAsString(myauthors));
			collection.insert(mydbObjects);
			return mydbObjects.get("_id").toString();
			
		} catch (Exception e) {
			System.out.println("error inserting author infor to mongo author collection");
			return null;
		}
	}

	public static void main(String[] args) {
		CreateAuthorCommand createMyAuthors = new CreateAuthorCommand();
		Author myAuthors = new Author();
		myAuthors.setFname("Gibsan");
		myAuthors.setLname("Abdu");
		Object id = createMyAuthors.execute(myAuthors);
		
		Author myAuthors2 = new Author();
		myAuthors2.setFname("Gibsan");
		myAuthors2.setLname("Abdu");
		Object id2 = createMyAuthors.execute(myAuthors2);
		
		
		if ( id!=null && id2!=null) {
			System.out.println(" Author One sucessfully Created:"+id);
			System.out.println(" Author Two sucessfully Created:"+id2);
		} else {
			System.out.println("Error : failed in creating Authors");
		}

	}
}
