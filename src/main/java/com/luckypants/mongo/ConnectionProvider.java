package com.luckypants.mongo;

import java.net.UnknownHostException;

import com.luckypants.properties.PropertiesLookup;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class ConnectionProvider {
	/**
	 * TODO:modify this method to allow passing the collection name to it
	 * @return
	 */

	public DBCollection getCollection(String collectionName) {
		try {
			PropertiesLookup mypro = new PropertiesLookup();
			MongoClient mongo = new MongoClient(mypro.getProperty("mongoDBURL"), Integer.parseInt(mypro.getProperty("mongoport")));

			DB db = mongo.getDB("luckypants");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			boolean auth = db.authenticate(mypro.getProperty("username"), mypro.getProperty("password").toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			DBCollection booksColl = db.getCollection(collectionName);
			return booksColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}


}
