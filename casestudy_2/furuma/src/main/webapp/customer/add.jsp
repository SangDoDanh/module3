<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/6/2022
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Add</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <jsp:include page="/header/header.jsp"/>
    <h1>Page Create New Customer</h1>
    <form action="/customer" method="post">
        <pre>
            <input type="text" hidden value="create" name="action">
        Name                <input type="text" placeholder="Name" name="name"><br>
                            <span style="color: red; font-size: 12px;">${validCustomerMap.get("name")}</span>
        CustomerTypeId      <select name="customerTypeId">
                                <c:forEach items="${customerTypeMap}" var="customerType">
                                    <option value="${customerType.key}">${customerType.value}</option>
                                </c:forEach>
                            </select><br>
        Birth day:          <input type="date" name="dayOfBirth"><br>
        gender              <input type="radio" name="gender" id="gender-male" value="1"> Nam    <input type="radio" name="gender" id="gender-female" value="0"> NU<br>
        IdCard:             <input type="text" placeholder="Id card" name="idCard"><br>
        Phone Number:       <input type="text" placeholder="phone number" name="phoneNumber"><br>
        Email:              <input type="text" placeholder="email" name="email"><br>
        Address:            <input type="text" placeholder="address" name="address"><br>
                            <input type="submit" value="Create">
        </pre>
    </form>
</div>
<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
