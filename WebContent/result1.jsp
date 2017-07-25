<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resultjsp.css"/>
<title>Frogs</title>
<c:choose>
		<c:when test="${frog != null}">
			<h2>Frog</h2>
			<ul>
				<li>${frog.name}</li>
				<li>${frog.lifespanYears}</li>
				<li>${frog.region}</li>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No frog found</p>
		</c:otherwise>
	</c:choose></head><br/>
	<form action="updateFrog.do" method="POST">
		<h2>update name:</h2>
		<input type="text" name="name" value="${frog.name}"/><br/>
		<h2>update lifespan in years:</h2>
		<input type="text" name="lifespanYears" value="${frog.lifespanYears}"/><br/>
		<h2>update region in years:</h2>
		<input type="text" name="region" value="${frog.region}"/><br/>
		<input type="submit" value="Update Frog" />
	</form>
	
	
<body>
	
</body>
</html>