<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome to Messages List</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
			<h2 id="article_header" class="text-warning" align="center">Spring Mvc and MongoDb Example</h2>
	    	<div>&nbsp;</div>

			<div id="add_new_group">
	    			<a id="addMessage" href="createMessage" class="btn btn-success">Create message</a>
	    	</div>
	    	<div>&nbsp;</div>
			
	    	<!-- Table to display the user list from the mongo database -->
	    	<table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Id</th><th>Message</th><th>Date</th><th>User sender</th><th>User reciever</th><th colspan="5"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${messageList}" var="message">
	                	<tr align="center">
	                    	<td><c:out value="${message.id}" /></td>
	                    	<td><c:out value="${message.message}" /></td>
	                    		<td><c:out value="${message.timestamp}" /></td>
	                    		<td><c:out value="${message.sender}" /></td>
	                    		<td><c:out value="${message.reciever}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="/edit-message-${message.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Update</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/delete-message-${message.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>