<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 10/5/2022
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Furuma Home</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../home/css/home.css">
</head>

<body class="bg-light">
<header>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="../home/images/logo.png" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex flex-wrap align-items-center" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/employee">Employee</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer">Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/facility">Service</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/contract">Contract</a>
                    </li>
                </ul>
                <form class="d-flex m-0 w-100 p-0 container" role="search">
                    <input class="form-control" id="txt-search" type="search" placeholder="Search" aria-label="Search">
                </form>
            </div>

        </div>
    </nav>

</header>
<script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
