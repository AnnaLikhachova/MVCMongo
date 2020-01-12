<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome to Group List</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
			<h2 id="article_header" class="text-warning" align="center">Spring Mvc and MongoDb Example</h2>
	    	<div>&nbsp;</div>

			<div id="add_new_group">
	    			<a id="addGroup" href="createChat" class="btn btn-success">Add Chat</a>
	    	</div>
	    	<div>&nbsp;</div>
			
	    	<!-- Table to display the user list from the mongo database -->
	    	<table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Id</th><th>Name</th><th colspan="3"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${chats}" var="chat">
	                	<tr align="center">
	                    	<td><c:out value="${chat.id}" /></td>
	                    	<td><c:out value="${chat.name}" /></td>
	                    	<td>
	                        	<c:url var="goToChat" value="/goto-chat-${chat.id}" />
								<a id="update" href="${goToChat}" class="btn btn-success">Go To Chat</a>
	                    	</td>
							<%--<td>
	                        	<c:url var="editChatUrl" value="/edit-chat-${chat.id}" />
								<a id="update" href="${editChatUrl}" class="btn btn-warning">Update</a>
	                    	</td>--%>
	                    	<td>
	                        	<c:url var="deleteChatUrl" value="/delete-chat-${chat.id}" />
								<a id="delete" href="${deleteChatUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>
