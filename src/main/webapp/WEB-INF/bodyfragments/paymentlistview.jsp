<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment List</title>
</head>
<body>

<div class="container bg-image bgimage">

<h2 style="padding: 30px">Payment List</h2>
<%@include file="businessMessage.jsp" %>
<table class="table bg-light text-dark">
  <thead>
    <tr>
      <th scope="col">Card no</th>
      <th scope="col">Name on Card</th>
      <th scope="col">Expire</th>
      <th scope="col">Price</th>
  
    </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${list}" var="li" varStatus="u">
    <tr>     
      <td>${li.cardNumber}</td>
      <td>${li.nameOnCard}</td>
      <td>${li.expireDate}</td>
      <td>${li.totalPrice}</td>
    </tr>
   </c:forEach>
  </tbody>
</table>

</div>

</body>
</html>