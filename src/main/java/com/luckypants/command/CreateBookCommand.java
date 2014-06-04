package com.luckypants.command;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.command.CreateBookCommand;
import com.luckypants.model.Book;
import com.luckypants.model.Author; 

import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
//import com.users.command.CreateUserCommand;


public class CreateBookCommand {

	public boolean execute(Book mybooks) {
		BooksConnectionProvider connectionBook = new BooksConnectionProvider();
		DBCollection tablebook = connectionBook.getCollection();

		ObjectMapper mymapperObj = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mymapperObj.writeValueAsString(mybooks)) ;
			tablebook.insert(dbObject);
		} catch (Exception e) {
			System.out.println("error inserting books to books collection on Mongo DB");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		CreateBookCommand  createmybooks = new CreateBookCommand ();
		CreateAuthorCommand createmyauthors = new CreateAuthorCommand();
		
		Author author = new Author();
		author.setFname("Gibsn");
		author.setLname("Abdu");
		
		Book mybooks= new Book();
		String _id = createmyauthors.execute(author);
		mybooks.setTitle("storeForntAspdotnet");
		mybooks.setISBN("12");
		mybooks.set_author_id(_id);
		
		ArrayList <String> genlist = new ArrayList<String>();
		genlist.add("E-commerce");
		genlist.add("DotNet Website");
		mybooks.setGenres(genlist);
		if (createmybooks.execute(mybooks)){
			System.out.println("books are succefully created");
			
		}else{
			System.out.println("failed in creating books");
		}
	}
}
		
		
		
		
		
		
	