<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/6/2022
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Contract</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
    <style>
        select{
            padding: 4px;
            border: none;
            border-radius: 2px;
        }
    </style>
</head>
<body class="container bg-light">
<jsp:include page="/header/header.jsp" />
    <h1>Contract Page</h1>
    <table class="table table-secondary table-hover">
<%--        start_date, end_date, deposit, employee_id, customer_id, facility_id--%>
        <tr class="table-active">
            <th>Dịch vụ</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Tiền đặt cọc</th>
            <th>Nhân viên</th>
            <th>Khách hàng</th>
            <td>Dịch vụ đi kèm</td>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        <c:forEach var="contract" items="${contractList}">
            <tr>
                <td>
                    ${facilityMap.get(contract.facilityID)}
                </td>
                <td>${contract.startDay}</td>
                <td>${contract.endDay}</td>
                <td>
                    <fmt:formatNumber value="${contract.deposit}" />
                </td>
                <td>
                    ${employeeMap.get(contract.employeeId)}
                </td>
                <td>
                    ${customerMap.get(contract.getCustomerId())}
                </td>
                <td>
                    <div class="d-flex align-items-center justify-content-around">
                        <button class="btn btn-sm btn-warning">+</button>
                        <select class="custom-select bg-warning text-light">
                            <option value="" hidden>Các dịch vụ đi kèm</option>
                            <c:forEach items="${attachMap}" var="attach">
                                <c:if test="${attach.key == contract.id}">
                                    <option class="bg-info" style="padding: 6px">
                                        <p style="padding: 6px">${attach.value}</p>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </td>
                <td>
                    <a href="" class="btn btn-danger">Delete</a>
                </td>
                <td>
                    <a href="" class="btn btn-info">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
