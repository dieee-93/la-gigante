<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>

<html>
<body>
	<h2>Hello World!</h2>
	
	<c:if test="${flash != null }">
	<c:out value="${flash }"></c:out>
	
	</c:if>

	<form action="login" method="post">

		<div class="mb-3">
			<label for="username" class="form-label">Usuario</label> <input
				class="form-control" name="username">
		</div>

		<div class="mb-3">
			<label for="password" class="form-label">Contraseña</label> <input
				type="password" class="form-control" name="password">
		</div>

		<div class="d-grid gap-2">
			<button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
		</div>
		
		<div class="d-grid gap-2">
			<a href="/lagigante/newuser.jsp"><button type="button" class="btn btn-lg btn-primary">Ingresar</button></a>
		</div>
		
	</form>
</body>
</html>
