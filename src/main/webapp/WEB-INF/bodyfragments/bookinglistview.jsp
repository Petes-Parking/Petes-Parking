<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Booking List</title>
  <style>
    .table-wrapper {
	  max-height: 600px;
	  overflow-y: scroll;
	  margin: 0px;
	}
	
	table { 
	  
	  border-collapse: collapse; 
	  width: 100%;
	}
	
	th {
	  position: sticky;
	  top: 0px;
	  z-index: 1;
	  background-color: #FFF;
	  border: 1px solid black;
	}
	
	td, th , table th{
	  padding: 10px;
	  border: 1px solid #ddd;
	  text-align: left;
	}
	 input[type="text"] {
      width: 100%;
      padding: 8px 8px;
      margin: 8px 0;
      box-sizing: border-box;
      border: 2px solid #ccc;
      border-radius: 4px;
      font-size: 16px;
    } 
      
  </style>
</head>
<body >

<div class="container" >
<div class="table-wrapper">

<h2 style="padding: 30px">Booking List</h2>
<%@include file="businessMessage.jsp" %>
<table class="table bg-light text-dark">
  <thead>
    <tr>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>     
      <th scope="col">Phone Number</th>
      <th scope="col">Parking Name</th>
      <th scope="col">Slot</th>
       <th scope="col">From Booking Date</th>     
      <th scope="col">From Time</th>
      <th scope="col">To Booking Date</th>
      <th scope="col">To Time</th>
      <th scope="col">Request Status</th>
      <th scope="col">Action</th>    
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="li" varStatus="u">
    <tr>     
      <td>${li.firstName}</td>
      <td>${li.lastName}</td>
      <td>${li.email}</td>
      <td>${li.phoneNumber}</td>
      <td>${li.parkingName}</td>
      <td>${li.slot}</td>
      <td>${li.fromBookingDate}</td>
      <td>${li.fromTime}</td>
      <td>${li.toBookingDate}</td>
      <td>${li.toTime}</td>
       <td>${li.reqstatus}</td>
      <td>
          
      <c:if test="${sessionScope.user.userRole == 'Admin'}">
      <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/approveReq?id=${li.id}">Approve</a>
       <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/declineReq?id=${li.id}">Decline</a>
        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/userCancel?id=${li.id}">Cancel</a>
      </c:if>
        <c:choose>
      <c:when test="${sessionScope.user.userRole == 'Admin'}">
      <div>
      <sf:form method="post" action="${pageContext.request.contextPath}/paymentRequest" modelAttribute="form">  
        <s:bind path="amount">
                  <font color="red" style="font-size: 5px"><sf:errors path="${status.expression}" /></font>
                  <sf:input type="text" id="amount" class="form-control" path="amount" placeholder="Amount" name="amount" style="width: 100px;"/>
                  </s:bind> 
                   <sf:input type="hidden" id="userId" class="form-control" path="userId" name="userId" value="${li.id }"/> 
                  
          <input type="submit" name="payment" value="Ticket">
         </sf:form>   
      </div>

      </c:when>
      
      <c:otherwise>
      
       <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/payment">Pay Due ${paymentRequestDTO.amount}</a>
      </c:otherwise>
      
      </c:choose>
      </td>
    </tr>
   </c:forEach>
  </tbody>
</table>
	<c:choose>
	  <c:when test="${sessionScope.user.userRole == 'Admin'}">
	    <td>
	      <form action="${pageContext.request.contextPath}/adminview" method="get">
	        <input type="hidden" name="id" value="${li.id}">
	        <button type="submit" class="btn btn-outline-primary">Back</button>
	      </form>
	    </td>
	  </c:when>
	  <c:otherwise>
	    <td>
	      <form action="${pageContext.request.contextPath}/main" method="get">
	        <input type="hidden" name="id" value="${li.id}">
	        <button type="submit" class="btn btn-outline-primary">Back</button>
	      </form>
	    </td>
	  </c:otherwise>
	</c:choose>

</div>
</div>
</body>
</html>