package com.luckypants.command;


import com.luckypants.command.DeleteBookCommand;
import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;


public class DeleteBookCommand {
	
	public boolean execute(String isbn) {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("iSBN", isbn);

		DBCursor cursor = booksCollection.find(searchQuery);

		while (cursor.hasNext()) {
			booksCollection.remove(searchQuery);
		}

		return true;
	}

	public static void main(String[] args) {
		DeleteBookCommand command = new DeleteBookCommand();
		boolean result =  command.execute("1234");
		System.out.println(result);
	}
	
}
