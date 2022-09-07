<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<div class="container my-1 g-0 mt-4 mb-0" id="materiaInfo-form">

	<div class="row">
		<div class="col-6">
			<input type="text" name="newMateria-nombre" id="newMateria-nombre-input" class="form-control"
				placeholder="Nombre" />
		</div>
		<div class="col-6">
			<select class="form-select" aria-label="Default select example"
				name="newMateria-categoria">
				<option value="0" selected>Categoria principal</option>
				<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
					<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
						<option value="${cat.id}">${cat.nombre}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>


		<div class="col-6 my-2">
			<select class="form-select" name="newMateria-tipo"
				id="newMateria-tipo-select">
				<option value="default" selected>Selecciona tipo de Materia</option>
				<option value="conmesurable">Conmesurable</option>
				<option value="contable">Contable</option>
				<option value="elaborada">Elaborada</option>
			</select>
		</div>
		<div class="col-6 my-2">
			<input type="number" step=".01" min="0" max="500000" required
				class="form-control" name="newMateria-costo" id="newMateria-costo-input"
				placeholder="Costo" />
		</div>

	</div>


</div>

<div class="container g-0 mb-3" id="materiaCantidad-form"
	style="display: none;">

	<div class="row">
		<div class="col-6">


			<input type="number" min="0" max="20000" name="newMateria-cantidad"
				id="newMateria-cantidad" class="form-control" placeholder="Cantidad" />


		</div>
		<div class="col-6 d-flex justify-content-center align-items-center" >
			<select class="form-select" name="newMateria-unidadDeMedida"
				id="newMateria-unidadDeMedida-select">
				<option value="kg">Kg</option>
				<option value="gr">g</option>
				<option value="l">L</option>
				<option value="ml">ml</option>
			</select>
			<div class="form-check" id="materiaContable-checkboxDiv">
				<input class="form-check-input" type="checkbox" value=""
					id="materiaUnitaria-checkbox" checked> <label
					class="form-check-label" for="flexCheckChecked">Unitaria</label>
			</div>
			
			<input type="text" name="newMateria-receta" id="newMateria-receta-input" class="form-control"
				placeholder="Nombre" style="display:none" value="">
			<button type="button" class="btn btn-outline-primary" id="nuevaReceta-btn" data-bs-toggle="modal" data-bs-target="#exampleModal">Nuevo ingrediente</button>
		
		</div>
	</div>
	

</div>


<script src="/assets/js/stockJS/newMateriaForm.js"></script>










