<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My profile</title>
	<link rel="stylesheet" type="text/css" href="./css/profile.css">
</head>
<body>
	<div class="container">
    <h2>My profile</h2>

    <% User user = (User) request.getAttribute("user"); %>

    <% if (user != null) { %>
        <p><strong>User ID:</strong> <%= user.getId() %></p>
        <p><strong>Username:</strong> <%= user.getUsername() %></p>
        <p><strong>Name:</strong> <%= user.getLastname() + user.getFirstname() %></p>
    <% } else { %>
        <p>User information not available. Please log in.</p>
    <% } %>
</div>
</body>
</html>