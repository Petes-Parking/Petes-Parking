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

<h2 style="padding: 20px">Pending Request</h2>
<%@include file="businessMessage.jsp" %>
<table class="table bg-light text-dark">
  <thead>
    <tr>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>
      <th scope="col">Phone Number</th>
      <th scope="col">DOB</th>      
      <th scope="col">Registration Status</th> 
       <th scope="col">Action</th> 
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${pendingList}" var="li" varStatus="u">
    <tr>     
      <td>${li.firstName}</td>
      <td>${li.lastName}</td>
      <td>${li.email}</td>
      <td>${li.phoneNumber}</td>
      <td>${li.dob}</td>
       <td>
       <a href="${pageContext.request.contextPath}/userApprove?id=${li.id}" class="btn btn-primary btn-lg active">Approve</a>
      <a href="${pageContext.request.contextPath}/userReject?id=${li.id}" class="btn btn-danger btn-lg">Reject</a>   
       </td>
    </tr>
   </c:forEach>
  </tbody>
</table>

       	<form action="${pageContext.request.contextPath}/adminview" method="get">
			  <input type="hidden" name="id" value="${li.id}">
			  <button type="submit" class="btn btn-outline-primary">Back</button>
		</form>
</div>

</body>
</html>