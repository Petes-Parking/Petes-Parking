<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Review Exp Report</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
          background-color: white;
          color: goldenrod;
        }

        .table-striped tbody tr:nth-of-type(odd) {
          background-color: goldenrod;
        }

        .table-striped tbody tr.selected {
          background-color: cornflowerblue;
        }

        .bg-oldgoldenrod .widget:hover {
          background-color: black;
          color: goldenrod;
        }

        .widget a {
          display: block;
          height: 100%;
          width: 100%;
          padding: 0 1rem;
          transition: color 0.3s ease;
        }

        .widget:hover a {
          color: black;
        }

        .bg-oldgoldenrod .widget:hover a {
          color: goldenrod;
        }

        .table-responsive {
          max-height: 75%;
          max-width: 110%;
        }

    </style>
</head>
<body>

    <div class="container">
      <h1 class="text-center">Review Expiration Reports</h1>
      <div class="table-responsive">
        <div class="row">
          <div class="col-md-12">
            <h3>Expiration Reports:</h3>
            <form action="" method="POST" id="expForm">
              <input type="hidden" name="expReports" id="expReports">
              <table class="table table-striped">
                <thead>
                <tr>
                  <th>Date Report Submitted</th>
                  <th>Parking Lot</th>
                  <th>Description</th>
                  <th>Reporter Email</th>
                  <th>View More Information</th>
                </tr>
                </thead>
                <tbody>
                <!-- Loop through the users and display the list -->
                <c:forEach items="${adminExpList}" var="report" varStatus="status">
                  <tr class="exp-row" data-report-id="${report.id}">
                    <td>${report.reportDate}</td>
                    <td>${report.parkingLot}</td>
                    <td>${report.description}</td>
                    <td>${report.reporterEmail}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/review-exp/${report.id}">View More</a></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>
</body>