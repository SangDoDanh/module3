<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/3/2022
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
</head>
<body>
<h1>User Manager</h1>
<a href="/user?action=add">ADD new User</a>

<h3>List Of User</h3>
<a class="btn btn-success" href="/user?action=sort">Sort By Name</a>
<div class="search">
    <form action="/user?action=search" method="post">
        <input type="text" placeholder="Search By Name..." name="search">
        <input type="submit" value="Search">
    </form>
</div>
<table class="table table-dark">
    <tr class="table-active">
        <th>#</th>
        <th>Name</th>
        <th>Email</th>
        <th>Country</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="count">
        <tr>
            <td>${count.count}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.country}</td>
            <td><a href="/user?action=edit&id=${user.id}">Edit</a></td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${user.id}">
                    Delete
                </button>

                <!-- Modal -->

                <div class="modal fade" id="exampleModal${user.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5 text-dark" id="exampleModalLabel">Delete user</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body text-dark">
                                <p>
                                    Delete user : <strong class="text-danger">${user.name}</strong>
                                </p>
                                <p>
                                    Id: <strong class="text-danger">${user.id}</strong>
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <a href="/user?action=delete&id=${user.id}">
                                    <button type="button" class="btn btn-danger">OK</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
