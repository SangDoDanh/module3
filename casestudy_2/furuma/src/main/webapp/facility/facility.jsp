<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Facility</title>
    <link rel="stylesheet" href="../bootstrap/bootstrap_5.2.2/css/bootstrap.min.css">
</head>
<body class="container">
<jsp:include page="/header/header.jsp" />
    <div class="">
        <h1>Facility</h1>
        <div class="row">
            <c:forEach items="${facilityList}" var="facility">
               <div class="col-lg-4" style="margin-bottom: 24px">
                   <div class="card">
                       <img src="../facility/images/villa.jpg" class="card-img-top" alt="...">
                       <div class="card-body">
                           <h5 class="card-title">${facility.name}</h5>
                           <p class="card-text">${facility.descriptionOtherConvenience}</p>
                           <p class="card-text text-danger">${facility.cost}đ / ${rentTypeMap.get(facility.rentTypeId)} | ${facilityTypeMap.get(facility.facilityTypeId)}</p>
                           <div class="d-flex justify-content-between">
                               <a href="#" class="btn btn-danger">Múc Ngay</a>
                               <a href="#" class="btn btn-outline-success">Xem Chi tiết</a>
                           </div>
                       </div>
                   </div>
               </div>
            </c:forEach>
        </div>
    </div>
    <script src="../bootstrap/bootstrap_5.2.2/js/bootstrap.min.js"></script>
</body>
</html>
