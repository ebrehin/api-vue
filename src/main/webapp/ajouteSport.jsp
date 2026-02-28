<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css.css" type="text/css">
<title>Ajouter un sport</title>
</head>
<body>
	<h3>Formulaire de création de sportif</h3>
	<form method="POST">
		<label for="intitule">Intiulé :</label><br>
		<input type="text" id="intitule" name="intitule"><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>