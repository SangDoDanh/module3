<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>product List</title>
    <script src="https://kit.fontawesome.com/68540fcb59.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../product/style.css">
</head>
<body class="d-flex justify-content-center">
<div class="app" style="width: 80%">
    <h1 class="m-4 text-center text-uppercase">Products Management</h1>
    <div style="display: flex;" class="my-4">
        <button style="height: 100%;margin-right:32px;" class="btn btn-success" id="btn-create-product">Create new Product</button>
        <form class="d-flex" role="search" style="flex: 1" action="/products?action=search" method="post">
            <input name="search" class="form-control me-2" type="search" placeholder="Search by name..." aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </div>
    <form class="py-4" method="post" action="/products" id="form-create-product">
        <h2>Create new Customer</h2>
<%--        name--%>
        <c:if test="${!nameValid && nameValid != null}">
            <input class="inValid" type="text" name="productName" placeholder="Name">

        </c:if>
        <c:if test="${nameValid || nameValid == null}">
            <input type="text" name="productName" placeholder="Name">
        </c:if>
<%--        end name--%>
<%--        description--%>
        <c:if test="${!descriptionValid && nameValid != null}">
            <input class="inValid" type="text" name="productDescription" placeholder="Description">

        </c:if>
        <c:if test="${descriptionValid || nameValid == null}">
            <input type="text" name="productDescription" placeholder="Description">
        </c:if>
<%--        end description--%>
<%--        producer--%>
        <c:if test="${!producerValid && nameValid != null}">
            <input class="inValid" type="text" name="productProducer" placeholder="Producer">

        </c:if>
        <c:if test="${producerValid || nameValid == null}">
            <input type="text" name="productProducer" placeholder="Producer">
        </c:if>
<%--        end producer--%>

<%--      price--%>
        <c:if test="${!priceValid && nameValid != null}">
            <input class="inValid" type="text" name="productPrice" value="0">
        </c:if>
        <c:if test="${priceValid || nameValid == null}">
            <input type="text" name="productPrice" value="0">
        </c:if>
<%--        end price--%>
        <input type="text" style="display: none" name="action" value="create">
        <input type="submit" value="New Product">
    </form>
    <table class="table table-secondary">
        <tr class="table-dark">
            <th>Name</th>
            <th>Description</th>
            <th>Producer</th>
            <th>Price</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:if test="${products == null}">
            <tr>
                <td colspan="6">
                    <h3 class="text-center">Not Found Product!</h3>
                </td>
            </tr>
        </c:if>
        <c:forEach items='${requestScope["products"]}' var="product">
            <tr>
                <td><a href="/customers?action=view&id=${product.getId()}">${product.getName()}</a></td>
                <td>${product.getDescription()}</td>
                <td>${product.getProducer()}</td>
                <td>${product.getPrice()}</td>
                <td>
                    <button class="btn-edit-product btn btn-primary" data-id="${product.getId()}"
                            data-name="${product.getName()}"
                            data-description="${product.getDescription()}"
                            data-producer="${product.getProducer()}"
                            data-price="${product.getPrice()}">
                        <i class="fa-solid mx-2 fa-pen-to-square"></i>Edit
                    </button>
                </td>
                <td>
                    <button class="btn-delete-product btn btn-danger" data-id="${product.getId()}"
                            data-name="${product.getName()}">
                        <i class="fa-solid fa-trash mx-2"></i>Delete
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>

<%--Page--%>
    <div class="pageCustomer py-2">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item">
                    <a class="page-link" href="<c:url value="/products?indexPage=${indexPage - 1}"/>">Previous</a>
                </li>
                <c:forEach var = "i" begin = "1" end = "${amountPage}">
                    <c:if test="${i == indexPage}">
                        <li class="page-item active">
                    </c:if>
                    <c:if test="${!(i == indexPage)}">
                        <li class="page-item">
                    </c:if>
                        <a class="page-link" href="<c:url value="/products?indexPage=${i}"/>">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="<c:url value="/products?indexPage=${indexPage + 1}"/>">Next</a>
                </li>
            </ul>
        </nav>
    </div>
<%--end page----------------------------------------------------------------------%>
    <form method="post" action="/products" id="form-edit-product" style="display: none; padding: 16px 0" >
        <h2>Edit Product</h2>
        <input type="text" name="productName" id="product-name-edit">
        <input type="text" name="productDescription" id="product-description-edit">
        <input type="text" name="productProducer" id="product-producer-edit">
        <input type="text" name="productPrice" id="product-price-edit">
        <input type="text" style="display: none" name="action" value="edit">
        <input id="txt-product-id" hidden  type="text" name="productId" value="">
        <input type="submit" value="Update">
    </form>
    <form method="post" action="/products" style="display: none">
        <input type="text" style="display: none" name="action" value="delete">
        <input id="productIdDelete" style="display: none" type="text" name="productId" value="">
        <input type="submit" value="Save" id="delete-submit">
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    let eCreateProduct = document.getElementById('btn-create-product');
    let eEditProduct = document.querySelectorAll(".btn-edit-product");
    let eDeleteProduct = document.querySelectorAll(".btn-delete-product");
    let eProductId = document.getElementById('txt-product-id');
        let eFormCreateProduct = document.getElementById('form-create-product');
    let eFormEditProduct = document.getElementById('form-edit-product');
    let ePageLink = document.querySelectorAll(".page-link");


    eEditProduct.forEach(function (value) {
        value.addEventListener('click', function (){
            let productId = value.getAttribute('data-id');
            document.getElementById('product-name-edit').value = value.getAttribute('data-name');
            document.getElementById('product-description-edit').value = value.getAttribute('data-description');
            document.getElementById('product-producer-edit').value = value.getAttribute('data-producer');
            document.getElementById('product-price-edit').value = value.getAttribute('data-price');
            eProductId.value = productId;
            eFormEditProduct.style.display = "block";
        });
    });

    eDeleteProduct.forEach(function (value) {
        value.addEventListener('click', function() {
            let productName = value.getAttribute('data-name');
            let productId = value.getAttribute('data-id');
            let deleteConfirm = confirm("xac nhan xoa "+ productName +" id: "+ productId);
            let eProductIdDelete = document.getElementById('productIdDelete');
            if(deleteConfirm) {
                eProductIdDelete.value = productId;
                document.getElementById('delete-submit').click();
            }
        })
    });
    eCreateProduct.addEventListener('click',function () {
        if(eFormCreateProduct.classList.contains('display-none')) {
            eFormCreateProduct.classList.remove('display-none')
        } else {
            eFormCreateProduct.classList.add('display-none');
        }
    });
</script>
</body>
</html>