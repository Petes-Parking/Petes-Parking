<%@page import="java.text.DateFormat"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page import="java.util.Date"%>

<body>
<section class="vh-100 gradient-custom ">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5">
           <%@include file="businessMessage.jsp" %>
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
            
             
            <sf:form method="post" action="${pageContext.request.contextPath}/addUser" modelAttribute="form">
            <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${form.id}"/>
              <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline">   
                  <s:bind path="firstName">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  <sf:input type="text" id="firstName" class="form-control form-control-lg" path="firstName" name="firstName"/>
                  </s:bind>      
                  <label class="form-label" for="firstName">First Name</label>
                  
                  </div>
				 
                </div>
                <div class="col-md-6 mb-4">
                
                  <div class="form-outline">
                  <s:bind path="lastName">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="text" id="lastName" class="form-control form-control-lg" path="lastName"/>
                    <label class="form-label" for="lastName">Last Name</label>
                  </s:bind>
                  </div>
               
                </div>
                
              </div>

              <div class="row">
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="dob">
                  <div class="form-outline datepicker w-100">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="date" class="form-control form-control-lg" id="dob" path="dob" placeholder="dd/MM/yyyy" />
                    <label for="birthdayDate" class="form-label">DOB</label>
                  </div>
                  </s:bind>

                </div>
                <div class="col-md-6 mb-4">
                
                  <h6 class="mb-2 pb-1">Gender: </h6>
                  <s:bind path="gender">
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"  id="gender"
                      value="Female" checked name="gender"/>
                    <label class="form-check-label" for="femaleGender">Female</label>
                  </div>
                  
                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"  id="gender"
                      value="Male" checked name="gender"/>
                    <label class="form-check-label" for="maleGender">Male</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="gender"
                      value="Other" checked name="gender" />
                    <label class="form-check-label" for="otherGender">Other</label>
                  </div>
				</s:bind>
                </div>
              </div>


              <div class="row">
                <div class="col-md-6 mb-4 pb-2">
                  <s:bind path="email">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="email" id="emailAddress" class="form-control form-control-lg"  path="email"/>
                    <label class="form-label" for="emailAddress">Email</label>
                  </div>
                  </s:bind>

                </div>
                  <div class="col-md-6 mb-4 pb-2">
                  <s:bind path="password">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="password" id="emailAddress" class="form-control form-control-lg" path="password"/>
                    <label class="form-label" for="emailAddress" >Password</label>
                  </div>
					</s:bind>
                </div>
                <div class="col-md-6 mb-4 pb-2">
                 <s:bind path="phoneNumber">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="tel" id="phoneNumber" class="form-control form-control-lg" path="phoneNumber" />
                    <label class="form-label" for="phoneNumber">Phone Number</label>
                  </div>
					</s:bind>
                </div> 
                
                <div class="row">
                <div class="col-12">               
                  <select class="select form-control-lg" name = "userRole">
                    <option value="1" disabled>Select Role</option>
                    <option value="Student">User</option>
                    <option value="Admin">Admin</option>
                  </select>
                  <label class="form-label select-label">Select User Role</label>
                </div>          
              </div>
                
              </div>
 

                <c:choose>
  				<c:when test="${form.id>0}">
   				  <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Update" />
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
</body>