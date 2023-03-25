
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<c:if test="${success != null}">
	<div  style="color: green;font-size: 17px;" role="alert">${success}</div>
</c:if>

<c:if test="${success2 != null}">
	<div  style="color: green;font-size: 17px;" role="alert">${success2}</div>
</c:if>
<c:if test="${success3 != null}">
	<div  style="color: green;font-size: 17px;" role="alert">${success3}</div>
</c:if>
<c:if test="${error != null}">
	<div style="color: red;font-size: 17px;" role="alert">${error}</div>
</c:if>