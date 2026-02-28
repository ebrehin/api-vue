<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css.css" type="text/css">
<title>Sportifs et pratiques</title>
</head>
<body>
	<h3>Liste des sportifs et de ce qu'ils font</h3>
	<c:forEach items="${requestScope.sportifs}" var="sp">
		<h4>${sp.nom}</h4>
		<p>
			<b>Adresse : </b>${sp.rue} &ndash; ${sp.code_postal} ${sp.ville}
		</p>
		<c:choose>
			<c:when test="${empty sp.disciplines}">
				<p>
					<i>Ne pratique aucune discipline sportive</i>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<b>Liste des disciplines pratiquées : </b>
				</p>
				<ul>
					<c:forEach items="${sp.disciplines}" var="disc">
						<li>${disc.sport} (du sport ${disc.sport.intitule})</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</c:forEach>

</body>
</html>