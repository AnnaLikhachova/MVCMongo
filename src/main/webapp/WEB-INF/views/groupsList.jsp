<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container">
			<h2 id="article_header" class="text-warning" align="center">Spring Mvc and MongoDb Example</h2>
	    	<div>&nbsp;</div>

			<div id="add_new_group">
	    			<c:url var="addGroupUrl" value="/group/add" /><a id="addGroup" href="${addGroupUrl}" class="btn btn-success">Add group</a>
	    	</div>
	    	<div>&nbsp;</div>
			
	    	<!-- Table to display the user list from the mongo database -->
	    	<table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Id</th><th>Name</th><th colspan="2"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${groups}" var="group">
	                	<tr align="center">
	                    	<td><c:out value="${group.id}" /></td>
	                    	<td><c:out value="${group.name}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="/group/edit?id=${group.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Update</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/group/delete?id=${group.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Delete</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>
