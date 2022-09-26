<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/26/2022
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Discount Calculator</title>
  </head>
  <body>
    <form action="/calc" method="post">
        <input required name="des" type="text" placeholder="Product Description">
        <input required name="price" type="text" placeholder="List Price">
        <input required name="discount" type="text" placeholder="Discount Percent">
      <input type="submit" value="Calculate Discount">
    </form>
  </body>
</html>
