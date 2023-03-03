<!DOCTYPE html>
<html>
<head>
  <title>Parking Lot</title>
  <style>
    <%@include file="/WEB-INF/css/parkinglot_style.css"%>
  </style>
  <link href="../css/parkinglot_style.css" rel="stylesheet" type="text/css">
</head>
<body>
<ol></ol><div class="inside">
  <div class="parkingLot">
    <div class="lotImage">
      <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/corec-lot.png" width="350" height="350" alt=""/>
    </div>
    <h2 style="text-align: center">COREC Parking Lot</h2>
    <p style="text-align: center"> France A. Cordova Recreational Sports Center
      355 N Martin Jischke Dr, West Lafayette, IN 47906</p>
    <div class="details">
      <div class="permits">
        <div class="permit_pic">
          <img src=https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
        </div>
        <p style="padding-top: 15px">ABC Permits Required</p>
      </div>
      <div class="hours">
        <div class="hours_pic">
          <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
        </div>
        <p style="padding-top: 15px">Permits Required 8am-5pm</p>
      </div>
      <div class="spots">
        <div class="spots_pic">
          <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
        </div>
        <p style="padding-top: 10px">100 Total Spots</p>
      </div>
    </div>
  </div>

  <c:if test="${sessionScope.user.userRole == 'Student'}">

    <button class="reserve-button">
      <a class="reserve-button"
         href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a></button>
  </c:if>
</div>

</body>
</html>