<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Discount Calculator</title>
  </head>
  <body>
    <form action="/display-discount" method="post">
        <input required name="description" type="text" placeholder="Enter Description">
        <input required name="price" type="text" placeholder="Enter Price">
        <input required name="discount" type="text" placeholder="Enter Discount Percent">
      <input type="submit" value="Calculate Discount">
    </form>
  </body>
</html>
