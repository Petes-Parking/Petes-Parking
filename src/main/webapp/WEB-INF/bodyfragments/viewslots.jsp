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

<h2 style="padding: 30px">Slots</h2>
<%@include file="businessMessage.jsp" %>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Slots</th>
      <th scope="col">Status</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="li" varStatus="u">
    <tr>     
      <td><span>Slot </span> ${li.slot}</td>
      <c:choose>
      <c:when test="${li.status == true}">
      <td class="bg-success">Available</td>   
        
      </c:when>     
      <c:otherwise>
      <td class="bg-danger">Booked</td>
      <c:if test="${sessionScope.user.userRole == 'Admin'}">
      <td><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/updateslot?id=${li.id}">Mark It Available</a></td>
      </c:if>
      </c:otherwise> 
      </c:choose>

    </tr>
   </c:forEach>
  </tbody>
</table>

</div>

</body>
</html>