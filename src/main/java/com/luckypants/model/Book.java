package com.luckypants.model;



public class Book {

	String title;
	String Author;
	
	
	public String getTitle(){
		return title;
	}
	
	public String getAuthor(){
		return Author;
	}
	
	public void setTitle( String title){
		this.title = title;
		
	}
	
	public void setAuthor(String author){
		this.Author = author;
	}
	public static void  main(String[] args){
		Book book= new Book();
		book.title ="Making book meaning full";
		book.Author ="Katy siera";
				
		System.out.println("Title:"+book.title);
		System.out.println("Autor:"+book.Author);
		
		Book book2 = new Book();
		
		book2.title ="Head first servlet";
		book2.Author ="bryan hasim";
		
		System.out.println("Title:"+book2.title);
		System.out.println("Autor:"+book2.Author);
		
	}
}