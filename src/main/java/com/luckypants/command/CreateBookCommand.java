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
import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
//import com.users.command.CreateUserCommand;


public class CreateBookCommand {

	public boolean execute(Book book) {
		BooksConnectionProvider bookConn = new BooksConnectionProvider();
		DBCollection bookCollection = bookConn.getCollection();

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper.writeValueAsString(book)) ;
			bookCollection.insert(dbObject);
		} catch (Exception e) {
			System.out.println("ERROR during mapping books to Mongo Object");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		CreateBookCommand  create = new CreateBookCommand ();
		Book book = new Book();
		//book.setAuthor("Gibsan");
		book.setTitle("FirstIos");
		book.setISBN("1234");
		if (create.execute(book)) {
			System.out.println("SUCCESS:Book Created");
		} else {
			System.out.println("ERROR:Failed to create book");
		}

	}
	
}
