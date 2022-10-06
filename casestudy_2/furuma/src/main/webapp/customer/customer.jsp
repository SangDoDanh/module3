<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/6/2022
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
</head>
<body class="container">
<jsp:include page="/header/header.jsp" />
<h1 style="margin-top: 32px">Page customer</h1>
<div class="d-flex my-3 justify-content-end align-items-center">
    <a href="/customer?action=create" class="btn btn-outline-info">+ Add</a>
    <form style="flex: 1;" action="/customer" method="post" class="d-flex justify-content-end m-0 p-0 align-items-center">
        <input type="text" name="action" value="search" hidden>
        <input class="form-control w-auto" type="text" placeholder="Search by name..." name="keySearch">
        <select class="form-select w-auto mx-4" name="customerType">
            <option value="" hidden>Type</option>
            <c:forEach items="${customerTypeMap}" var="customerType">
                <option value="${customerType.key}">${customerType.value}</option>
            </c:forEach>
        </select>
        <input class="btn btn-outline-success" type="submit" value="Search">
    </form>

</div>
<table class="table table-secondary table-hover container">
    <tr class="table-active">
        <th>#</th>
        <th>Name</th>
        <th>Birth Day</th>
        <th>Email</th>
        <th>Type</th>
        <th>DELETE</th>
        <th>EDIT</th>
    </tr>
    <c:forEach items="${customerList}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.getDate()}</td>
            <td>${customer.email}</td>
            <td>${customerTypeMap.get(customer.customerTypeId)}</td>
            <td>
                <button onclick="deleteCustomer(`${customer.id}`, `${customer.name}`)" type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
                    DELETE
                </button>
            </td>
            <td><a href="#" class="btn btn-warning">EDIT</a></td>
        </tr>
    </c:forEach>
</table>

<!-- Modal Delete-->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-light">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Delete Customer</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Bạn có muốn xóa
                    <span id="name-customer" class="text-danger"></span>
                    id: <span id="id-customer" class="text-danger"></span>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                <form action="/customer" method="post">
                    <input type="text" name="id" id="id-delete" hidden>
                    <input type="text" name="action" value="delete" hidden>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    const eIdCustomerDelete = document.getElementById('id-customer');
    const eNameCustomerDelete = document.getElementById('name-customer');
    function deleteCustomer(id, name){
        console.log(id);
        eIdCustomerDelete.innerText = id;
        eNameCustomerDelete.innerText = name;
        document.getElementById('id-delete').value = id;
    }
</script>
<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
