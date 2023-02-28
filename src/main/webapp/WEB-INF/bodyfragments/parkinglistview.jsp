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
<title>User List</title>
</head>
<body >

<div class="container" >

<h2 style="padding: 30px">Parking List</h2>
<%@include file="businessMessage.jsp" %>
<table class="table bg-light text-dark">
  <thead>
    <tr>
      <th scope="col">Parking Name</th>
      <th scope="col">Address</th>
      <th scope="col">Number of Slots</th>    
      <c:choose>
      <c:when test="${sessionScope.user.userRole=='Admin'}">   
      <th scope="col">Action</th>
      </c:when>
      </c:choose>
      <th scope="col">View Parking Slot</th>
      <th scope="col">Book</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="li" varStatus="u">
    <tr>     
      <td>${li.parkingName}</td>
      <td>${li.address}</td>
      <td>${li.numberOfSlot}</td>
     
      <c:choose>
      <c:when test="${sessionScope.user.userRole=='Admin'}">  
       <td> 
      <a href="${pageContext.request.contextPath}/parkingEdit?id=${li.id}">Edit</a>
      <a href="${pageContext.request.contextPath}/parkingDelete?id=${li.id}">Delete</a> 
      </td>
      </c:when>
      </c:choose>     
      
       <td>      
      <a href="${pageContext.request.contextPath}/viewparkingSlot?id=${li.id}">View</a>    
      </td>
      <td>      
      <a href="${pageContext.request.contextPath}/booking?id=${li.id}">Book</a>    
      </td>
    </tr>
   </c:forEach>
  </tbody>
</table>

</div>

</body>
</html>