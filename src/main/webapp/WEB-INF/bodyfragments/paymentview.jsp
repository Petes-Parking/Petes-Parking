<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<head>
<style>
body {
	background-color: #D2B48C;
}
</style>

</head>
<section class="vh-100 gradient-custom bg-image bgimage">

	<div class="container py-10 h-100">
		<div class="row justify-content-center align-items-center h-100">
			<div class="col-12 col-lg-9 col-xl-7">
				<div class="card shadow-2-strong card-registration"
					style="border-radius: 15px;">
					<div class="card-body p-4 p-md-5">
						<%@include file="businessMessage.jsp"%>
						<h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Payment</h3>
						<p class="fw-bold mb-4 pb-2">Saved cards:</p>
            <div class="d-flex flex-row align-items-center mb-4 pb-1">
              <img class="img-fluid" src="https://img.icons8.com/color/48/000000/mastercard-logo.png" />
              <div class="flex-fill mx-3">
                <div class="form-outline">
                  <input type="text" id="formControlLgXc" class="form-control form-control-lg"
                    value="**** **** **** 3193" />
                  <label class="form-label" for="formControlLgXc">Card Number</label>
                </div>
              </div>
              <a href="#!">Remove card</a>
            </div>

            <div class="d-flex flex-row align-items-center mb-4 pb-1">
              <img class="img-fluid" src="https://img.icons8.com/color/48/000000/visa.png" />
              <div class="flex-fill mx-3">
                <div class="form-outline">
                  <input type="text" id="formControlLgXs" class="form-control form-control-lg"
                    value="**** **** **** 4296" />
                  <label class="form-label" for="formControlLgXs">Card Number</label>
                </div>
              </div>
              <a href="#!">Remove card</a>
            </div>

            <p class="fw-bold mb-4">Add new card:</p>

          <sf:form method="post" action="${pageContext.request.contextPath}/addPayment" modelAttribute="form">

            <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${form.id}"/>

              <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline">
                  <s:bind path="cardNumber">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                  <sf:input type="text" id="cardNumber" class="form-control form-control-lg" path="cardNumber" name="cardNumber"/>
                  </s:bind>
                  <label class="form-label" for="cardNumber">Card Number</label>

                  </div>

                </div>
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                  <s:bind path="nameOnCard">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="text" id="nameOnCard" class="form-control form-control-lg" path="nameOnCard"/>
                    <label class="form-label" for="nameOnCard">Name On Card</label>
                  </s:bind>
                  </div>

                </div>

              </div>

              <div class="row">
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <s:bind path="expireDate">
                  <div class="form-outline datepicker w-100">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="date" class="form-control form-control-lg" id="expireDate" path="expireDate" placeholder="dd/MM/yyyy"/>
                    <label for="expireDate" class="form-label">Expire Date</label>
                  </div>
                  </s:bind>

                </div>
              </div>


                <div class="col-md-6 mb-4 pb-2">
                 <s:bind path="totalPrice">
                  <div class="form-outline">
                  <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>
                    <sf:input type="tel" id="totalPrice" class="form-control form-control-lg" path="totalPrice" value="${totalPrice}" readonly="true"/>
                    <label class="form-label" for="totalPrice">Total Price</label>
                  </div>
					</s:bind>
                </div>

                <c:choose>
  				<c:when test="${form.id>0}">
   				  <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="submit" value="Update" />
              </div>
 			</c:when>

  			<c:otherwise>
             <div class="mt-4 pt-2 text-center">
                <input class="btn btn-primary btn-lg" type="submit" value="Pay" />
              </div>
  			</c:otherwise>
			</c:choose>

        </sf:form>

						<form action="${pageContext.request.contextPath}/main"
							method="get">
							<input type="hidden" name="id" value="${li.id}">
							<button type="submit" class="btn btn-outline-primary">Back</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</section>