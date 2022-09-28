<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<jsp:include page="/partials/head.jsp"></jsp:include>
<jsp:include page="/partials/utils.jsp"></jsp:include>
<link rel="stylesheet" href="assets/css/stylesheets/datatables.css" />
<link rel="stylesheet" href="assets/css/stylesheets/producto-card.css" />
<script type="text/javascript">
	let erasmo = $
	{
		productosJSON
	};
	let materia = $
	{
		materiasJSON
	};
</script>


</head>
<body>

	<jsp:include page="/partials/adminbar.jsp"></jsp:include>

	<main class="container-fluid">


		<div class="row d-flex justify-content-center">
			<div class="page-description col-10 box">
				<h2>Bienvenido a la tienda!</h2>

				<p>Aqui podras gestionar todos tus productos y crear promociones
				</p>
			</div>
			<div class="col-10 box px-0">
				<ul class="nav nav-tabs nav-fill justify-content-center"
					id="tiendaTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="producto-tab"
							data-bs-toggle="tab" data-bs-target="#producto-div" type="button"
							role="tab" aria-controls="producto" aria-selected="true">Productos</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="promocion-tab" data-bs-toggle="tab"
							data-bs-target="#promocion-div" type="button" role="tab"
							aria-controls="promocion" aria-selected="false">Promociones</button>
					</li>

				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="producto-div"
						role="tabpanel" aria-labelledby="home-tab">
				
						<c:choose>
							<c:when test="${productos.isEmpty()}">
								<p>
									Parece que no has agregado productos todavia
									<button type="button" class="btn btn-outline-info"
										onclick="mostrarFormulario('newProducto-form')">+</button>
								</p>

								<form class="form" action="newProducto.do" method="post"
									id="newProducto-form" style="display: none;">

									<jsp:include
										page="/views/admin/tienda/producto/form/newProducto.jsp"></jsp:include>

									<button type="submit" class="btn btn-danger">Crear
										Producto</button>



								</form>
							</c:when>

							<c:otherwise>
							
							
								<ul class="list-group">

									<c:forEach items="${productos}" var="prod">
										<ul class="cards">
											<li><div class="card"> <img
													src="assets/img/pizza.JPG" class="card__image"
													alt="" />
													<div class="card__overlay">
														<div class="card__header">
															<svg class="card__arc" xmlns="http://www.w3.org/2000/svg">
																<path /></svg>
															<img class="card__thumb"
																src="https://i.imgur.com/7D7I6dI.png" alt="" />
															<div class="card__header-text">
																<h3 class="card__title">${prod.nombre }</h3>
																<span class="card__status">Agregado el ${prod.fechaDeCreacion}</span>
															</div>
														</div>
														<p class="card__description">${prod.descripcion}</p>
													</div>
											</div></li>

										</ul>
										
									</c:forEach>
								</ul>
							</c:otherwise>
						</c:choose>
					</div>

					<div class="tab-pane fade" id="promocion-div" role="tabpanel"
						aria-labelledby="profile-tab"></div>
				</div>
			</div>

		</div>




		<div class="row">
			<div class="col-6"></div>
			<div class="col-6"></div>
		</div>
	</main>
	<script src="assets/js/tiendaJS/newProductoForm.js"></script>
</body>
</html>
