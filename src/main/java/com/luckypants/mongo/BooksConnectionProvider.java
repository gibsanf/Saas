package com.luckypants.mongo;

import java.net.UnknownHostException;



import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class BooksConnectionProvider {

	public DBCollection getCollection() {
		try {

			MongoClient mongo = new MongoClient("kahana.mongohq.com", 10018);

			DB db = mongo.getDB("luckypants");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			boolean auth = db.authenticate("unh", "unh".toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			DBCollection booksColl = db.getCollection("books");
			return booksColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		BooksConnectionProvider books = new BooksConnectionProvider();
		DBCollection booksCollection = books.getCollection();
		if(booksCollection == null){
			System.out.println("ERROR:No Connection");
		}
		else{
			System.out.println("SUCCESS:Connected");
		}

	}
	
}
