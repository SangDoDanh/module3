<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/5/2022
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Employee</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
</head>
<body>
    <h1>Page Employee</h1>

    <table class="table table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Birth Day</th>
            <th>Email</th>
            <th>Address</th>
            <th>Position</th>
            <th>Education</th>
            <th>Division</th>
            <th>salary</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${employeeList}" var = "e">
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <th>${e.dateOfBirth}</th>
                <td>${e.email}</td>
                <td>${e.address}</td>
                <td>
                    ${e.positionId}
                </td>
                <td>${e.educationDegreeId}</td>
                <td>${e.divisionId}</td>
                <td>
                    <fmt:formatNumber type="number" maxFractionDigits = "3" value="${e.salary}" />
                </td>
                <td>
                    <a href="#" class="btn btn-primary">Edit</a>
                </td>
                <td>
                    <a href="#" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
