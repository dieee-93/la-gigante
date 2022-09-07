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
			if (id == "newCategory-form"){
			boton.innerText = "-";
			boton.classList.replace('btn-success', 'btn-danger');}
		}
		else {
			document.getElementById(id).style.display = "none";
			if (id == "newCategory-form"){
			boton.innerText = "+";
			boton.classList.replace('btn-danger', 'btn-success');
			}
		}
		
		}
		
	


	function getTree() {

		var data = ${categoriasJSON};
		if (data.length == 0){
			data = [{text: 'No existen categorias aún <button type="button" class="btn btn-success float-end" id="newCategoryForm-btn"><i class="fa-solid fa-plus"></i></button>', icon: "fa-solid fa-circle-xmark"}];
		}
		return data;
	}
	
	let erasmo = {
			  <c:forEach items="${listaDeMateria}" var="materia">
			  "${materia.nombre}": {
				id:"${materia.id}",
			    categoria:"${materia.categoria}",
			    tipo:"${materia.tipo}",
			    costo:"${materia.costo}",
			    cantidad:"${materia.cantidad}",
			    unidadDeMedida:"${materia.getUnidadDeMedida()}",
			    precioMinimo:"${materia.getPrecioMinimo()}",
			    precioUnitario:"${materia.getPrecioUnitario()}",
			  },
			  </c:forEach>
			}
	

</script>
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

				<div id="treeview" class="js-treeview"></div>
				<button type="button" class="btn btn-info"
					onclick="mostrarFormulario('newCategory-form')">
					<i class="fa-solid fa-plus"></i>
				</button>

				<form action="newCategory.do" method="post" id="newCategory-form"
					style="display: none;">
					<jsp:include page="/views/admin/stock/form/newCategory.jsp"></jsp:include>
				</form>

			</div>

			<div class="col-6 newmateria-div">
				<h3>Lista de Stock</h3>

				<ul class="list-group">

					<c:choose>
						<c:when test="${listaDeMateria.isEmpty()}">
							<li class="list-group-item disabled">Tu Stock se encuentra
								vacio, por favor agrega un articulo.</li>

						</c:when>
						<c:otherwise>
							<c:forEach items="${listaDeMateria}" var="materia">
								<li class="list-group-item">${materia.nombre} ${materia.cantidad} ${materia.unidadDeMedida}</li>
							</c:forEach>
						</c:otherwise>

					</c:choose>


				</ul>
				<div class="row">
					<div class="col-12 d-flex justify-content-around">
						<c:choose>
							<c:when test="${listaDeMateriaEnStock.isEmpty()}">
								<button type="button" class="btn btn-outline-secondary">No
									hay materias primas.</button>
								<button type="button" class="btn btn-outline-info"
									onclick="mostrarFormulario('newMateria-form')">Nueva
									Materia Prima</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-outline-secondary">Recibo
									Proovedores</button>
								<button type="button" class="btn btn-outline-info"
									onclick="mostrarFormulario('newMateria-form')">Nueva
									Materia Prima</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>


				<form action="newMateria.do" method="post" id="newMateria-form"
					style="display: none;">

					<jsp:include page="/views/admin/stock/form/newMateria.jsp"></jsp:include>

					<button type="submit" class="btn btn-danger">Crear Materia</button>
 
		
				</form>
				<form id="newMateriaElaborada">
				<jsp:include page="/views/admin/stock/form/newMateriaElaborada.jsp"></jsp:include>
				</form>
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
	<script src="assets/js/stockJS/newMateriaForm.js"></script>
</body>
</html>