<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="/partials/head.jsp"></jsp:include>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<link rel="stylesheet"
	href="assets/plugins/bstreeview/bstreeview.min.css" />
<script src="assets/plugins/bstreeview/bstreeview.min.js"></script>
<script type="text/javascript">

	function mostrarFormulario(id){
		if(document.getElementById(id).style.display == "none")
		{
			document.getElementById(id).style.display = "block";
			boton.innerText = "-";
			boton.classList.replace('btn-success', 'btn-danger');
		}
		else {
			document.getElementById(id).style.display = "none";
			boton.innerText = "+";
			boton.classList.replace('btn-danger', 'btn-success');
		
		}
		
	}


	function getTree() {

		var data = ${categoriasJSON};
		if (data.length == 0){
			data = [{text: 'No existen categorias aún <button type="button" class="btn btn-success" id="newCategoryForm-btn"><i class="fa-solid fa-plus"></i></button>', icon: "fa-solid fa-circle-xmark"}];
		}
		return data;
	}
</script>
</head>
</head>
<body>
	<c:if test="${flash!= null}">
		<div class="alert alert-secondary" role="alert">
			<c:out value="${flash}"></c:out>
		</div>

	</c:if>

	<main class="container"><jsp:include
			page="/partials/adminbar.jsp"></jsp:include>




		<div class="row">
			<div class="col-6 newcategory-div">
				<h3>Categorias</h3>
				<form action="newCategory.do" method="post" id="newCategory-form" style="display:none;">

					<input type="text" name="newcategory-name" /> <select
						class="form-select" aria-label="Default select example"
						name="category-select">
						<option value="0" selected>Categoria principal</option>
						<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
							<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
								<option value="${cat.id}">${cat.nombre}</option>
							</c:forEach>
						</c:if>

					</select>
					<button type="submit" class="btn btn-danger">Crear
						Categoria</button>

				</form>
			<div id="treeview" class="js-treeview"></div>
			</div>

			<div class="col-6 newmateria-div">
				<h3>Lista de Stock</h3>
				
				<div class="row">
				<div class="col-12">	<c:choose>
				<c:when test="${listaDeMateria.isEmpty()}"> 
		<button type="button" class="btn btn-outline-secondary">No hay materias primas.</button>
			<button type="button" class="btn btn-outline-info" onclick="mostrarFormulario('newMateria-form')">Nueva Materia Prima</button>
				</c:when>
				<c:otherwise>
			<button type="button" class="btn btn-outline-secondary">Nueva existencia Stock</button>
			<button type="button" class="btn btn-outline-info">Nueva Materia Prima</button>
				</c:otherwise>
				</c:choose></div>
				</div>
				<form action="newMateria.do" method="post" id="newMateria-form" style="display: none;">
					<input type="text" name="newMateria-name"
						placeholder="Nombre materia" /> <input type="text"
						name="newMateria-cantidad" placeholder="Peso" /> <select
						class="form-select" aria-label="Default select example"
						name="newMateria-unidadDeMedida-select">
						<option value="kg">Kg</option>
						<option value="gr">Gr</option>
						<option value="l">L</option>
						<option value="ml">ml</option>
					</select><input type="text" name="newMateria-costo" placeholder="Costo" />
					<input type="number" name="newMateria-unidades"
						placeholder="Cantidad" /><select class="form-select"
						aria-label="Default select example"
						name="newMateria-category-select">
						<option value="0" selected>Categoria principal</option>
						<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
							<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
								<option value="${cat.id}">${cat.nombre}</option>
							</c:forEach>
						</c:if>

					</select>

					<button type="submit" class="btn btn-danger">Guardar</button>
				</form>
						<ul class="list-group">
				
				<c:choose>
				<c:when test="${stock.getMateriaEnStock().isEmpty()}">
				<li class="list-group-item disabled">Tu Stock se encuentra vacio, por favor agrega un articulo.</li>
				</c:when>
				</c:choose>
				
				
				</ul>
			</div>


		</div>

		<div class="row">
			<div class="col-6">
			
			</div>
			<div class="col-6">
		
			</div>
		</div>




	</main>



	<script type="text/javascript">
	
		$('#treeview').bstreeview({
			data : getTree()
		})
		
			let boton = document.querySelector('#newCategoryForm-btn');
			
			boton.addEventListener('click', () => mostrarFormulario('newCategory-form') );
	</script>
</body>
</html>