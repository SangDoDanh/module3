<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/3/2022
  Time: 7:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>add user</title>
</head>
<body>
    <h1>Add new user</h1>
    <a href="/user">Back to home</a>
        <span>${mess}</span>
    <form method="post" action="/user?action=create">
        <label for="name">name</label>
        <input id="name" type="text" value="${name}" name="name" placeholder="User Name"><br>
        <label for="email">email</label>
        <input id="email" type="text" value="${email}" name="email" placeholder="User email"><br>
        <label for="country">country</label>
        <input id="country" type="text" value="${country}" name="country" placeholder="User country"><br>
        <input type="submit" value="Create user">
    </form>
</body>
</html>
