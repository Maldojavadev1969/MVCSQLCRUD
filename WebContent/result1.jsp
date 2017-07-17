<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Frogs</title>
<c:choose>
		<c:when test="${! empty frog}">
			<ul>
				<li>${frog.name}</li>
			</ul>
			<ul>
				<li>${frog.name}</li>
				<li>${frog.lifespanYears}</li>
				<li>${frog.region}</li>
			</ul>
			
		</c:when>
		<c:otherwise>
			<p>No frog found</p>
		</c:otherwise>
	</c:choose></head>
<body>
	
</body>
</html>