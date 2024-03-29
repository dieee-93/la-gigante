<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<jsp:include page="/partials/head.jsp"></jsp:include>
<jsp:include page="/partials/utils.jsp"></jsp:include>
<link rel="stylesheet" href="assets/css/stylesheets/datatables.css" />
<link rel="stylesheet"
	href="assets/css/stylesheets/tienda/producto-card.css" />
<link rel="stylesheet"
	href="assets/css/stylesheets/tienda/producto-view.css" />

<link rel="stylesheet"
	href="assets/plugins/bstreeview/bstreeview.min.css" />
<script src="assets/plugins/bstreeview/bstreeview.min.js"></script>

<script type="text/javascript">
	let erasmo = ${productosJSON};
	let materia = ${materiasJSON};
	let cate = ${categoriasProducto};
	let categoriasMateria = ${categoriasMateria};
	
	function getTree() {

	var data = ${categoriasProducto}
		;
		if (data.length == 0) {
			data = [ {
				text : 'No existen categorias aún <button type="button" class="btn btn-success float-end" id="newCategoryForm-btn"><i class="fa-solid fa-plus"></i></button>',
				icon : "fa-solid fa-circle-xmark"
			} ];
		}
		return data;
	}
</script>


</head>
<body>

	<jsp:include page="/partials/adminbar.jsp"></jsp:include>



	<c:if test="${sessionScope.success != null}">
		<div class="alert alert-success" role="alert">
			<c:out value="${sessionScope.success}" />
		</div>
		<c:remove var="success" scope="session" />
	</c:if>

	<c:if test="${sessionScope.producto != null}">
		<div class="alert alert-danger" role="alert">
			<c:out value="Error al crear el producto" />
			<ul>
				<c:forEach items="${sessionScope.producto.errors}" var="error">
					<li>${error.value}</li>
				</c:forEach>
			</ul>

		</div>
		<c:remove var="producto" scope="session" />
	</c:if>

	<main class="container-fluid">


		<div class="row d-flex justify-content-center">
			<div class="page-title col-10 d-flex flex-column align-items-end ">
				
				<div class="d-flex flex-row align-items-end justify-content-center">
				<h1 class="title-text">Tienda</h1>
				<img alt="Logo de la tienda" src="/lagigante/assets/img/icon/tienda-icon-orange.png" class="title-icon">
				
				
				</div>
				

				<p>Gestiona tus productos y crea promociones
				</p>
			</div>
			<div class="page-content col-10 px-0 box">
				<ul class="nav nav-pills nav-fill justify-content-center"
					id="tiendaTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active btn-info" id="producto-tab"
							data-bs-toggle="tab" data-bs-target="#producto-div" type="button"
							role="tab" aria-controls="producto" aria-selected="true">Productos</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link btn-info" id="promocion-tab" data-bs-toggle="tab"
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



								<div class="row px-0 mx-2">
									<div class="col-12">
										<div class="d-flex justify-content-around my-2">
											<div class="col-4 d-flex justify-content-around">
												<button class="btn btn-outline-primary"
													type="button" name="nuevoProductoBtn">
													<i class="fa-solid fa-burger"></i> Nuevo Producto
												</button>
												<button class="btn btn-outline-primary"
													type="button" name="nuevaCategoriaBtn"
													onclick="mostrarFormulario('newCategory-form')">
													<i class="fa-solid fa-folder"></i> Nueva Categoria
												</button>
											</div>
											<div class="col-8 d-flex align-items-center">
													<select class="selectpicker w-100" data-style="btn-dark"
								title="Buscador" data-live-search="true"
								id="buscadorProductos">
								<c:if test="${!listaDeMateria.isEmpty()}">
									<optgroup label="Categorias">
										<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
											<option value="${cat.id}">${cat.nombre}</option>
										</c:forEach>
									</optgroup>
								</c:if>
								<c:if test="${!productos.isEmpty()}">
									<optgroup label="Productos">
										<c:forEach items="${productos}" var="prod">
											<option value="${prod.id}">${prod.nombre}</option>
										</c:forEach>
									</optgroup>

								</c:if>



							</select>

											</div>
										</div>
									</div>
									<div class="col-4 px-0 mx-0" id="producto-action-div">

										<form action="newProdCategory.do" method="post"
											id="newCategory-form" style="display: none;">
											<jsp:include page="/views/admin/stock/form/newCategory.jsp"></jsp:include>

											<div
												class="my-2 mx-3 d-flex justify-content-end align-items-center form-action">
												<button type="submit" class="btn btn-outline-success btn-lg">
													<i class="fa-solid fa-check"></i>
												</button>
											</div>


										</form>

										<div id="treeview" class="js-treeview"></div>
									</div>
									<div class="col-8 px-0 mx-0 switchable" id="producto-show-div">


										<div class="sw-home default justify-content-center">
											<h1 class="h1 align-self-center">Selecciona una
												categoria para ver sus productos</h1>
										</div>

										<div id="nuevoProductoForm">


											<form class="form" action="newProducto.do" method="post"
												id="newProducto-form">
												<div
													class="my-4 mx-3 d-flex align-items-center justify-content-end">
												
														<i class="fa-solid fa-x st-input"></i>
										
												</div>

												<jsp:include
													page="/views/admin/tienda/producto/form/newProducto.jsp"></jsp:include>
												
												<div
													class="my-4 mx-3 d-flex align-items-center justify-content-end">
												
														<button type="submit" class="btn btn-danger">Crear
													Producto</button>
										
												</div>
												



											</form>

										</div>

										<div id="productoCards" class="switchable">

											<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">

												<c:forEach items="${mainCategory.getAllCategorias()}"
													var="cat">
													
													<h1>${cat.nombre}
											</h1>
													
													<ul id="cat-${cat.id}" class="cards-prod">


														<c:if test="${!cat.contenido.isEmpty()}">
															<c:forEach items="${cat.contenido}" var="prod">

																<li><a href="" class="card-prod"> <img
																		src="assets/img/pizza.JPG" class="card__image" alt="" />
																		<div class="card__overlay">
																			<div class="card__header">
																				<div class="card__header-text">
																					<h3 class="card__title">${prod.nombre}</h3>
																					<span class="card__status">Creado el
																						${prod.fechaDeCreacion}</span>
																				</div>
																			</div>
																			<p class="card__description">Lorem ipsum dolor
																				sit amet consectetur adipisicing elit. Asperiores,
																				blanditiis?</p>
																		</div>
																</a></li>
															</c:forEach>
														</c:if>


													</ul>
												</c:forEach>




											</c:if>


										</div>





									</div>
								</div>




							</c:otherwise>
						</c:choose>
					</div>

					<div class="tab-pane fade" id="promocion-div" role="tabpanel"
						aria-labelledby="profile-tab"></div>
				</div>
			</div>

		</div>
<jsp:include page="producto/form/agregarIngredienteModal.jsp"></jsp:include>


	</main>
	<script type="text/javascript">
		$('#treeview').bstreeview({
			data : getTree()
		})
	</script>

	<script src="assets/js/tiendaJS/newProductoForm.js"></script>


</body>
</html>
