<%@page import="java.text.DateFormat"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
<section class="vh-100 gradient-custom ">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5">
           <%@include file="businessMessage.jsp" %>
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Book Parking</h3>
            <sf:form method="post" action="${pageContext.request.contextPath}/addBooking" modelAttribute="form">
            <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${form.id}"/>
              <div class="row">
              
                <div class="col-md-6 mb-4">
                  <div class="form-outline">   
                  <s:bind path="firstName">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  <sf:input type="text" id="firstName" class="form-control form-control-lg" path="firstName" name="firstName" value="${user.firstName}" readonly="true"/>
                  </s:bind>      
                  <label class="form-label" for="firstName">firstName</label>
                  
                  </div>
				 
                </div>
                <div class="col-md-6 mb-4">
                
                  <div class="form-outline">
                  <s:bind path="lastName">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="text" id="lastName" class="form-control form-control-lg" path="lastName" value="${user.lastName}" readonly="true"/>
                    <label class="form-label" for="lastName">lastName</label>
                  </s:bind>
                  </div>
               
                </div>
                
              </div>

              <div class="row">

                <div class="col-md-6 mb-4 pb-2">
                 <s:bind path="email">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="tel" id="email" class="form-control form-control-lg" path="email" value="${user.email}" readonly="true"/>
                    <label class="form-label" for="email">Email</label>
                  </div>
					</s:bind>
                </div> 
                
                <div class="col-md-6 mb-4 pb-2">
                 <s:bind path="phoneNumber">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="tel" id="phoneNumber" class="form-control form-control-lg" path="phoneNumber" value="${user.phoneNumber}" readonly="true"/>
                    <label class="form-label" for="phoneNumber">Phone Number</label>
                  </div>
					</s:bind>
                </div> 
                
              </div>
              
                <div class="row">

                <div class="col-md-6 mb-4 pb-2">
                 <s:bind path="parkingName">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="tel" id="parkingName" class="form-control form-control-lg" path="parkingName" value="${parkingDTO.parkingName}" readonly="true"/>
                    <label class="form-label" for="parkingName">parkingName</label>
                  </div>
					</s:bind>
                </div> 
                
               <div class="col-md-6 mb-4 pb-2">    
               <label class="form-label select-label">Select slot</label>             
                 
                  <select class="select form-control-lg" name="slotId">
                  <option value="1" disabled>Select slot</option>
                    <c:forEach items="${slotList}" var="list" varStatus="u">
                    <option value="${list.id}">${list.slot}</option>
                    </c:forEach>
                  </select>
                  
                </div>
  
                
              </div>
              
                <div class="row">
                
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="fromBookingDate">
                  <div class="form-outline datepicker w-100">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="date" class="form-control form-control-lg" id="fromBookingDate" path="fromBookingDate" placeholder="dd/MM/yyyy" min="<%= df.format(new java.util.Date())%>"/>
                    <label for="fromBookingDate" class="form-label">fromBookingDate</label>
                  </div>
                  </s:bind>
                </div>
                
                
                 <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="fromTime">
                  <div class="form-outline datepicker w-100">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="time" class="form-control form-control-lg" id="fromTime" path="fromTime" placeholder="HH:MM:SS"/>
                    <label for="fromTime" class="form-label">fromTime</label>
                  </div>
                  </s:bind>
                </div>
                
                </div>
              
               <div class="row">
                
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="toBookingDate">
                  <div class="form-outline datepicker w-100">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="date" class="form-control form-control-lg" id="toBookingDate" path="toBookingDate" placeholder="dd/MM/yyyy" min="<%= df.format(new java.util.Date())%>"/>
                    <label for="toBookingDate" class="form-label">toBookingDate</label>
                  </div>
                  </s:bind>
                </div>
                
                
                 <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="toTime">
                  <div class="form-outline datepicker w-100">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="time" class="form-control form-control-lg" id="toTime" path="toTime" placeholder="HH:MM:SS"/>
                    <label for="toTime" class="form-label">toTime</label>
                  </div>
                  </s:bind>
                </div>
                
                </div>
 
                <c:choose>
  				<c:when test="${form.id>0}">
   				  <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Add" />
              </div>   
 			</c:when>

  			<c:otherwise>
             <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Add" />
              </div>
  			</c:otherwise>
			</c:choose> 

           </sf:form>
          
          </div>
        </div>
      </div>
    </div>
  </div>
</section>