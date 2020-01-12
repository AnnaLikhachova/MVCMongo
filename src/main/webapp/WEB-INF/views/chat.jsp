<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Chat</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container" modelAttribute="chat">
	<h2 id="article_header" class="text-warning" align="center">Spring Mvc and MongoDb Example</h2>
	<div>&nbsp;</div>
	<!-- Table to display the user list from the mongo database -->
	<table id="users_table" class="table">
		<thead>
		<tr align="center">
			<th>Message</th><th colspan="3"></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${messages}" var="message">
			<tr align="center">
				<td><c:out value="${message.message}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:url var="addMessageUrl" value="/createMessage" />
	<form:form id="user_form" modelAttribute="messageAttr" method="POST" action="${addMessageUrl}">
		<div id="add_new_message">
			<form:input id="group_name" cssClass="form-control" path="message" />
			<button id="addGroup" type="submit" class="btn btn-success">Add Message</button>
		</div>
	</form:form>

</div>
</body>
</html>
