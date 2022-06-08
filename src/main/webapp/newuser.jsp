<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
</head>
<body>
	<form action="create" method="post">

		<div class="mb-3">
			<label for="username" class="form-label">Email</label> <input
				class="form-control" name="email">
		</div>

		<div class="mb-3">
			<label for="password" class="form-label">Contraseña</label> <input
				type="password" class="form-control" name="password">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Telefono</label> <input
				type="password" class="form-control" name="telefono">
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Direccion</label> <input
				type="password" class="form-control" name="direccion">
		</div>


		<div class="d-grid gap-2">
			<button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
		</div>


	</form>
</body>
</html>