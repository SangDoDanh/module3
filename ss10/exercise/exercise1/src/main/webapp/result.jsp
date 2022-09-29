<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Danh sach khach hang</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Birth Day</th>
            <th>Photo</th>
        </tr>
        <c:forEach items="${customerList}" var="customer">
            <tr>
                <td>${customer.getName()}</td>
                <td>${customer.getDayOfBirth()}</td>
                <td>${customer.getAddress()}</td>
                <td><img width="50" src="${customer.getUrlImage()}" alt=""></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
