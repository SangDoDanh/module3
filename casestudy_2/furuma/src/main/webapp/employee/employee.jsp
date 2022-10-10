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
    <link rel="stylesheet" href="../customer/css/customer.css">
</head>
<body class="container">
<jsp:include page="/header/header.jsp" />
    <h1 style="margin-top: 32px">Page Employee</h1>

    <table class="table table-striped table-hover">
        <tr class="table-active">
            <th>ID</th>
            <th>Name</th>
            <th>Birth Day</th>
            <th>Email</th>
            <th>Address</th>
            <th>Position</th>
            <th>Education</th>
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
                    ${positionMap.get(e.positionId)}
                </td>
                <td>${educationMap.get(e.educationDegreeId)}</td>
                <td>
                    <fmt:formatNumber type="number" maxFractionDigits = "3" value="${e.salary}" />
                </td>
                <td>
                    <button onclick="editCustomer(`${e.id}`, `${e.name}`, `${e.idCard}`, `${e.dateOfBirth}`, `${e.salary}`, `${e.phoneNumber}`, `${e.email}`, `${e.address}`, `${e.positionId}`, `${e.educationDegreeId}`, `${e.divisionId}`)" type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editModal">
                        <span class="text-light">Edit</span>
                    </button>
                </td>
                <td>
                    <button onclick="deleteEmployee(`${e.name}`, `${e.id}`)" type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal">
                        DELETE
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content border-0">
            <div class="modal-header bg-danger">
                <h1 class="modal-title fs-5 text-light" id="deleteModalLabel">Delete Customer</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p class="text-capitalize">Do you want to delete Employee?</p>
                <p class="px-4">Name: <span id="name-employee" class="text-danger mb-2"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Close</button>
                <form action="/employee" method="post">
                    <input type="text" name="id" id="id-delete" hidden>
                    <input type="text" name="action" value="delete" hidden>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%--Modal edit--%>
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/employee" method="post" id="form-edit">
            <div class="modal-content border-0" >
                <div class="modal-header bg-warning">
                    <h1 class="modal-title fs-5" id="editModalLabel">Edit Employee</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body group-input">
                    <div class="d-flex align-items-center">
                        <label for="name">Name: </label>
                        <input class="w-75" type="text" placeholder="Name" id="name" name="name">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="idCard">Id card: </label>
                        <input class="w-75" type="text" placeholder="Name" id="idCard" name="idCard">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="dateOfBirth">Birth day: </label>
                        <input class="w-75" type="date" id="dateOfBirth" name="dayOfBirth">
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="positionId">Position: </label>
                        <select class="w-75" name="positionId" id="positionId">
                            <c:forEach items="${positionMap}" var="position">
                                <option value="${position.key}">${position.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="educationID">Education: </label>
                        <select class="w-75" name="educationID" id="educationID">
                            <c:forEach items="${educationMap}" var="education">
                                    <option value="${education.key}">${education.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="educationID">Division: </label>
                        <select class="w-75" name="divisionID" id="divisionID">
                            <c:forEach items="${divisionMap}" var="division">
                                <option value="${division.key}">${division.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex align-items-center">
                        <label for="salary">Salary: </label>
                        <input class="w-75" type="text" placeholder="Id card" name="salary" id="salary">
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
<%--end modal edit--%>
<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
<script>
    function deleteEmployee(name, id){
        document.getElementById('name-employee').innerText = name;
        document.getElementById('id-delete').value = id;
    }
    function editCustomer(id,name, idCard, date, salary,phoneNumber, email , address, positionId, eudcation, division) {
        let eEducation = document.querySelectorAll('#educationID option');
        let eDivision = document.querySelectorAll('#divisionID option');
        let ePositionId = document.querySelectorAll('#positionId option');
        document.getElementById('name').value = name;
        document.getElementById('idCard').value = idCard;
        document.getElementById('dateOfBirth').value = date;
        document.getElementById('salary').value = parseFloat(salary);
        document.getElementById('phoneNumber').value = phoneNumber;
        document.getElementById('email').value = email;
        document.getElementById('address').value = address;
        document.getElementById('idEdit').value = id;
        eEducation.forEach(function (option) {
            if(eudcation == option.value)
                option.setAttribute('selected', 'true');
        });
        eDivision.forEach(function (option) {
            if(division == option.value)
                option.setAttribute('selected', 'true');
        });
        ePositionId.forEach(function (option) {
            console.log("a: " +option.value);
            if(positionId == option.value)
                option.setAttribute('selected', 'true');
        });
        console.log(positionId);
    }
</script>
</body>
</html>
