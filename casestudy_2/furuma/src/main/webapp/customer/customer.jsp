<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="bg-light">
<head>
    <title>Customer</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../customer/css/customer.css">
</head>
<body class="container bg-light">
<jsp:include page="/header/header.jsp" />
<h1 style="margin: 48px 0">Customer manager</h1>
<div class="d-flex my-3 justify-content-end align-items-center">
    <a href="/customer?action=create" class="btn btn-outline-info">+ Add</a>
    <form style="flex: 1;" action="/customer" method="get" class="d-flex justify-content-end m-0 p-0 align-items-center">
        <input type="text" name="action" value="search" hidden>
        <input class="form-control w-auto mx-2" type="text" placeholder="Search by name..." name="keySearch">
        <select class="form-select w-auto mx-2" name="genderSearch">
            <option value="" hidden>Gender</option>
            <option value="1">Male</option>
            <option value="0">FeMale</option>
        </select>
        <select class="form-select w-auto mx-2" name="customerTypeSearch">
            <option value="" hidden>Type</option>
            <c:forEach items="${customerTypeMap}" var="customerType">
                <option value="${customerType.value}">${customerType.value}</option>
            </c:forEach>
        </select>
        <input class="btn btn-outline-success mx-2" type="submit" value="Search" style="margin-right: 0!important;">
    </form>

</div>
<table class="table table-striped container">
    <tr class="table-active">
        <th>#</th>
        <th>Name</th>
        <th>Birth Day</th>
        <th>Gender</th>
        <th>Email</th>
        <th>Type</th>
        <th>DELETE</th>
        <th>EDIT</th>
    </tr>
    <c:forEach items="${customerList}" var="customer" varStatus="customerCount">
        <tr>

            <td>${customerCount.count}</td>
            <td>${customer.name}</td>
            <td>${customer.getDate()}</td>
            <td>
                <c:if test="${customer.gender==1}">
                    <span class="text-info">Male</span>
                </c:if>
                <c:if test="${customer.gender==0}">
                    <span class="text-warning">FeMale</span>
                </c:if>
            </td>
            <td>${customer.email}</td>
            <td class="text-capitalize">${customerTypeMap.get(customer.customerTypeId)}</td>
            <td>
                <button onclick="deleteCustomer(`${customer.email}`, `${customer.name}`, `${customer.id}`)" type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal">
                    DELETE
                </button>
            </td>
            <td>
                <button onclick="editCustomer(`${customer.id}`, `${customer.name}`, `${customer.getDate()}`, `${customer.idCard}`, `${customer.phoneNumber}`, `${customer.email}`, `${customer.address}`, `${customer.customerTypeId}`)" type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editModal">
                    <span class="text-light">Edit</span>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Modal Delete-->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content border-0">
            <div class="modal-header bg-danger">
                <h1 class="modal-title fs-5 text-light" id="deleteModalLabel">Delete Customer</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p class="text-capitalize">Do you want to delete customer?</p>
                <p class="px-4">Name: <span id="name-customer" class="text-danger mb-2"></span></p>
                <p class="px-4">Email: <span id="email-customer" class="text-danger"></span></p>

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
                <div class="modal-body group-input">
                    <div class="d-flex align-items-center">
                        <label for="name">Name: </label>
                        <input class="w-75" type="text" placeholder="Name" id="name" name="name">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="customerType">Customer type: </label>
                        <select class="w-75" name="customerTypeId" id="customerType">
                            <c:forEach items="${customerTypeMap}" var="customerType">
                                <option value="${customerType.key}">${customerType.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="dayOfBirth">Birth day: </label>
                        <input class="w-75" type="date" name="dayOfBirth" id="dayOfBirth">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="idCard">Id card: </label>
                        <input class="w-75" type="text" placeholder="Id card" name="idCard" id="idCard">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="phoneNumber">Phone number:</label>
                        <input class="w-75" type="text" placeholder="phone number" id="phoneNumber" name="phoneNumber">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="email">Email:</label>
                        <input class="w-75" type="text" placeholder="email" name="email" id="email">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="address">Address:</label>
                        <input class="w-75" type="text" placeholder="address" name="address" id="address">
                    </div>
                    <div class="d-flex align-items-center justify-content-center">
                        <label for="chk-male">
                            <input type="radio" name="gender" value="1" checked id="chk-male">
                            Male
                        </label>
                        <label for="chk-female">
                            <input type="radio" name="gender" value="0" id="chk-female">
                            FeMale
                        </label>
                    </div>
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
    const eEmailCustomerDelete = document.getElementById('email-customer');
    const eNameCustomerDelete = document.getElementById('name-customer');
    function deleteCustomer(email, name, id){
        eEmailCustomerDelete.innerText = email;
        eNameCustomerDelete.innerText = name;
        document.getElementById('id-delete').value = id;
    }
    function editCustomer(id,name, date, idCard,phoneNumber, email , address, customerTypeId) {
        let eCustomerType = document.querySelectorAll('#customerType option');
        document.getElementById('name').value = name;
        document.getElementById('dayOfBirth').value = date;
        document.getElementById('idCard').value = idCard;
        document.getElementById('phoneNumber').value = phoneNumber;
        document.getElementById('email').value = email;
        document.getElementById('address').value = address;
        document.getElementById('idEdit').value = id;
        console.log(eCustomerType);
        eCustomerType.forEach(function (option) {
           if(customerTypeId == option.value)
            option.setAttribute('selected', 'true');
        });


    }
</script>
<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
