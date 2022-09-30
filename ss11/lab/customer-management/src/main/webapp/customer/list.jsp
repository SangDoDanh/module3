<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <script src="https://kit.fontawesome.com/68540fcb59.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css">
    </head>
<body class="d-flex justify-content-center">
<div class="app" style="width: 80%">
    <h1 class="m-3 text-center text-uppercase">Customers Management</h1>
    <div style="display: flex;">
        <button style="height: 100%;margin-right:32px;" class="btn btn-success" id="btn-create-customer">Create new customer</button>
        <form class="d-flex" role="search" style="flex: 1">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
    <table class="table table-secondary">
        <tr class="table-dark">
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items='${requestScope["customers"]}' var="customer">
            <tr>
                <td><a href="/customers?action=view&id=${customer.getId()}">${customer.getName()}</a></td>
                <td>${customer.getEmail()}</td>
                <td>${customer.getAddress()}</td>
                <td>
                    <button class="btn-edit-customer btn btn-primary" data-id="${customer.getId()}"
                            data-name="${customer.getName()}"
                            data-email="${customer.getEmail()}"
                            data-address="${customer.getAddress()}">
                        <i class="fa-solid mx-2 fa-pen-to-square"></i>Edit
                    </button>
                </td>
                <td>
                    <button class="btn-delete-customer btn btn-danger" data-id="${customer.getId()}"
                            data-name="${customer.getName()}">
                        <i class="fa-solid fa-trash mx-2"></i>Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form method="post" action="/customers" id="form-create-customer" style="display: none">
        <h2>Create new Customer</h2>
        <input type="text" name="customerName" placeholder="Name">
        <input type="email" name="customerEmail" placeholder="Email">
        <input type="text" name="customerAddress" placeholder="Address">
        <input type="text" style="display: none" name="action" value="create">
        <input type="submit" value="Create New Customer">
    </form>
    <form method="post" action="/customers" id="form-edit-customer" style="display: none">
        <h2>Chinh sua customer</h2>
        <input type="text" name="customerName" id="customer-name-edit">
        <input type="email" name="customerEmail" id="customer-email-edit">
        <input type="text" name="customerAddress" id="customer-address-edit">
        <input type="text" style="display: none" name="action" value="edit">
        <input id="txt-customer-id" style="display: none" type="text" name="customerId" value="">
        <input type="submit" value="Save">
    </form>
    <form method="post" action="/customers" id="form-delete-customer" style="display: none">
        <input type="text" style="display: none" name="action" value="delete">
        <input id="customerIdDelete" style="display: none" type="text" name="customerId" value="">
        <input type="submit" value="Save" id="delete-submit">
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
        let eCreateCustomer = document.getElementById('btn-create-customer');
        let eEditCustomer = document.querySelectorAll(".btn-edit-customer");
        let eDeleteCustomer = document.querySelectorAll(".btn-delete-customer");
        let eCustomerId = document.getElementById('txt-customer-id');
        let eFormCreateCustomer = document.getElementById('form-create-customer');
        let eFormEditCustomer = document.getElementById('form-edit-customer');


        eEditCustomer.forEach(function (value, key, parent) {
            value.addEventListener('click', function (){
                let customerId = value.getAttribute('data-id');
                document.getElementById('customer-name-edit').value = value.getAttribute('data-name');
                document.getElementById('customer-email-edit').value = value.getAttribute('data-email');
                document.getElementById('customer-address-edit').value = value.getAttribute('data-address');
                eCustomerId.value = customerId;
                eFormEditCustomer.style.display = "block";

            });
        });

        eDeleteCustomer.forEach(function (value) {
            value.addEventListener('click', function() {
                let customerName = value.getAttribute('data-name');
                let customerId = value.getAttribute('data-id');
                let deleteConfirm = confirm("xac nhan xoa "+ customerName +" id: "+ customerId);
                let eCustomerIdDelete = document.getElementById('customerIdDelete');
                if(deleteConfirm) {
                    eCustomerIdDelete.value = customerId;
                    document.getElementById('delete-submit').click();
                }
            })
        });
        eCreateCustomer.addEventListener('click',function () {
            eFormCreateCustomer.style.display = "block";
        });
</script>
</body>
</html>