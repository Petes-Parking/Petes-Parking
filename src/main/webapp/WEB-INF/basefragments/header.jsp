<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--	crossorigin="anonymous"></script>--%>
<%--<script--%>
<%--	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"--%>
<%--	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"--%>
<%--	crossorigin="anonymous"></script>--%>
<%--<script--%>
<%--	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"--%>
<%--	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"--%>
<%--	crossorigin="anonymous"></script>--%>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ddceac;">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<div class="float-left">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/login"></a></li>

					<c:if test="${sessionScope.user == null}">

					</c:if>

					<c:if test="${sessionScope.user.userRole == 'Admin'}">

					</c:if>

					<c:if test="${sessionScope.user.userRole == 'Student'}">


						<li class="nav-item"><a class="nav-link active"
												aria-current="page"
												href="${pageContext.request.contextPath}/main">Map</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>

						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/bookinglist">Booking</a></li>


						<li class="nav-item dropdown">
							<a class="nav-link active dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

						<c:if test="${empty notifications}">

						<img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/notification_bell.png" style="width: 25px; height: 25px;">
						</c:if>
								<c:if test="${not empty notifications}">

									<img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/notification_bell_alert.png" style="width: 25px; height: 25px;">
								</c:if>

							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">

								<c:if test="${empty notifications}">
									<p style="overflow: visible; position: absolute; z-index: 9999; color: red;">No notifications found.</p>

								</c:if>



								<c:if test="${not empty notifications}">
									<c:forEach items="${notifications}" var="notif" varStatus="status">
<%--										<a href="${pageContext.request.contextPath}/${notif.pageToGoTo}">--%>

										<a href="${pageContext.request.contextPath}/${notificationUrls[status.index]}">

										<p style="z-index: 10; color: green;">${notif.message}</p>
										</a>
									<span class="timestamp">${notif.timeStamp}</span>
										<span><hr></span>

									</c:forEach>
								</c:if>


<%--								<c:forEach var="notification" items="${notifications}">--%>
<%--&lt;%&ndash;									<a class="dropdown-item" href="0">&ndash;%&gt;--%>
<%--											${notification}--%>
<%--&lt;%&ndash;										<span class="timestamp">${notification}</span>&ndash;%&gt;--%>
<%--&lt;%&ndash;									</a>&ndash;%&gt;--%>
<%--									<!-- Debug: print notification.message -->--%>
<%--									<c:out value="TEST ${notification}" />--%>
<%--								</c:forEach>--%>
							</div>
						</li>


						<li class="nav-item"><a class="nav-link active"
												aria-current="page"
												href="${pageContext.request.contextPath}/logout">Logout</a></li>

					</c:if>

				</ul>
			</div>
			<div class="float-right" style="margin-left: 700px">
				<ul class="navbar-nav">
					<c:if test="${sessionScope.user != null}">
				
					</c:if>
				</ul>
			</div>
		</div>
	</div>


</nav>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>