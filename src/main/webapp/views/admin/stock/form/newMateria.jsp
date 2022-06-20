<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="row">

	<div class="col-12 d-flex" id="materiaInfo-form">
		<input type="text" name="newMateria-name" placeholder="Nombre materia" />

		<select class="form-select" aria-label="Default select example"
			name="newMateria-category-select">
			<option value="0" selected>Categoria principal</option>
			<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
				<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
					<option value="${cat.id}">${cat.nombre}</option>
				</c:forEach>
			</c:if>
		</select> <select class="form-select" name="newMateria-tipo-select">
			<option value="default" selected>Selecciona tipo de Materia</option>
			<option value="conmesurable">Conmesurable</option>
			<option value="contable">Contable</option>
			<option value="elaborada">Elaborada</option>
		</select>
	</div>
	<div class="col-12" id="materiaCantidad-form">


		<input type="text" name="newMateria-cantidad" placeholder="Cantidad" />

		<select class="form-select" name="newMateria-unidadDeMedida-select">
			<option value="kg">Kg</option>
			<option value="gr">g</option>
			<option value="l">L</option>
			<option value="ml">ml</option>
		</select> <input type="text" name="newMateria-costo" placeholder="Costo" />
	</div>
</div>







