package com.luckypants.command;

import java.util.ArrayList;
import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllAuthorsCommand {
	
	public ArrayList<Author> execute(){
		ConnectionProvider myConnection = new ConnectionProvider();
		DBCollection myAuthorsCollection = myConnection.getCollection("authors");
		
		DBCursor cursor = myAuthorsCollection.find();
		
		ArrayList <Author> authorslist = new ArrayList<Author>();
		GetAuthorCommand getauthors = new GetAuthorCommand();
		
	try{
		while (cursor.hasNext()){
			Author myauthors = getauthors.execute("_id", cursor.next().get("_id").toString());
			System.out.println(myauthors) ;
			authorslist.add(myauthors); 
		}
	}finally{
		cursor.close();
	}return authorslist;
		
	}

	public static void main( String[] args){
		ListAllAuthorsCommand myauthorslist = new ListAllAuthorsCommand();
		ArrayList<Author> mylist = myauthorslist.execute();
		System.out.print(mylist);
	}
	
}
