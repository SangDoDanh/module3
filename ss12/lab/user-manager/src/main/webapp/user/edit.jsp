<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/3/2022
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
    <h1>TRang edit</h1>
    <form method="post" action="/user?action=update">
        <label for="id">id</label>
        <input id="id" type="text" value="${id}" name="id"><br>
        <label for="name">name</label>
        <input id="name" type="text" value="${name}" name="name"><br>
        <label for="email">email</label>
        <input id="email" type="text" value="${email}" name="email"><br>
        <label for="country">country</label>
        <input id="country" type="text" value="${country}" name="country"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
