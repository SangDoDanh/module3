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
    <link rel="stylesheet" href="../customer/css/customer.css">
</head>
<body class="container">
<jsp:include page="/header/header.jsp" />
<h1 style="margin-top: 32px">Page customer</h1>
<div class="d-flex my-3 justify-content-end align-items-center">
    <a href="/customer?action=create" class="btn btn-outline-info">+ Add</a>
    <form style="flex: 1;" action="/customer" method="post" class="d-flex justify-content-end m-0 p-0 align-items-center">
        <input type="text" name="action" value="search" hidden>
        <input class="form-control w-auto" type="text" placeholder="Search by name..." name="keySearch">
        <select class="form-select w-auto mx-4" name="customerTypeSearch">
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
            <td>
                <button onclick="editCustomer(`${customer.id}`, `${customer.name}`, `${customer.getDate()}`, `${customer.idCard}`, `${customer.phoneNumber}`, `${customer.email}`, `${customer.address}`)" type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editModal">
                    Edit
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Modal Delete-->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-light">
                <h1 class="modal-title fs-5" id="deleteModalLabel">Delete Customer</h1>
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
<!-- End Modal Delete-->

<!-- Modal edit-->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/customer" method="post" id="form-edit">
            <div class="modal-content border-0" >
                <div class="modal-header bg-warning">
                    <h1 class="modal-title fs-5" id="editModalLabel">Edit Customer</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input class="w-100" type="text" placeholder="Name" id="name" name="name"><br>
                    <select class="w-100" name="customerTypeId">
                        <c:forEach items="${customerTypeMap}" var="customerType">
                            <option value="${customerType.key}">${customerType.value}</option>
                        </c:forEach>
                    </select><br>
                    <input class="w-100" type="date" name="dayOfBirth" id="dayOfBirth"><br>
                    <input type="radio" name="gender" value="1" checked> Nam    <input type="radio" name="gender" value="0"> NU<br>
                    <input class="w-100" type="text" placeholder="Id card" name="idCard" id="idCard"><br>
                    <input class="w-100" type="text" placeholder="phone number" id="phoneNumber" name="phoneNumber"><br>
                    <input class="w-100" type="text" placeholder="email" name="email" id="email"><br>
                    <input class="w-100" type="text" placeholder="address" name="address" id="address"><br>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-warning" data-bs-dismiss="modal">Close</button>
                        <input type="text" name="id" hidden id="idEdit">
                        <input type="text" name="action" value="edit" hidden>
                        <button type="submit" class="btn btn-warning">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- End Modal edit-->

<script>
    const eIdCustomerDelete = document.getElementById('id-customer');
    const eNameCustomerDelete = document.getElementById('name-customer');
    function deleteCustomer(id, name){
        console.log(id);
        eIdCustomerDelete.innerText = id;
        eNameCustomerDelete.innerText = name;
        document.getElementById('id-delete').value = id;
    }
    function editCustomer(id,name, date, idCard,phoneNumber, email , address) {
        document.getElementById('name').value = name;
        document.getElementById('dayOfBirth').value = date;
        document.getElementById('idCard').value = idCard;
        document.getElementById('phoneNumber').value = phoneNumber;
        document.getElementById('email').value = email;
        document.getElementById('address').value = address;
        document.getElementById('idEdit').value = id;
        console.log(document.getElementById('date'));

    }
</script>
<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
