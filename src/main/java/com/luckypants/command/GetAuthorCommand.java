package com.luckypants.command;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GetAuthorCommand {
	
	ObjectMapper myMapper = new ObjectMapper();
	
	public Author execute(String ids, String value){
	 ConnectionProvider myConn = new ConnectionProvider();
	 DBCollection myAuthorsCollection = myConn.getCollection("authors");
	 
	 BasicDBObject fetchquesry = new BasicDBObject();
	 if (ids.equals("_id")){
		 fetchquesry.put(ids, new ObjectId(value));
	 }else{
		 fetchquesry.put(ids, value);
	 }
		DBObject myauthors = myAuthorsCollection.findOne(fetchquesry);
		 
		Author authors = null;
		 try{
			 authors = myMapper.readValue(myauthors.toString(), Author.class);
			 System.out.println("FirstName:"+authors.getFname());
			return authors ;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return null ;
	}
	public static void main(String[] args) {
		GetAuthorCommand command = new GetAuthorCommand();

		Author b = command.execute("_id", "538e6fe230045bba1d93eb54");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
