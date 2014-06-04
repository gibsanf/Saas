<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>




<%@ page import="com.luckypants.model.Book" %>

Lucky pants book
<br>
<%
Book book1 = new Book();
book1.setTitle(request.getParameter("title"));
book1.setAuthor(request.getParameter("author"));
%>
<br/>
YOU ARE SUCCESSFULLY CREATED A BOOK
<br />

TITLE: <%=book1.getTitle() %> <br/>
AUTHOR: <%=book1.getAuthor() %> <br/>


