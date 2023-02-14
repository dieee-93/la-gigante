<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container g-0" id="producto-form">
	<div class="row">
		<div class="container-fluid">

			<div class="row">
				<div class="col-6 mb-2">
					<input type="text" name="newProducto-nombre"
						id="newProducto-nombre-input" class="form-control st-input" 
						placeholder="Nombre del Producto" />
				</div>
				<div class="col-6 mb-2">
				 <select class="form-select st-input"
						aria-label="Default select example" name="newProducto-categoria">
						<option value="0" selected>Categoria principal</option>
						<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
							<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
								<option value="${cat.id}">${cat.nombre}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<div class="col-12">

					<textarea class="form-control st-input" name="newProducto-descripcion"
						id="newProducto-descripcion-input" rows="4"
						placeholder="Inserte una breve descripcion del articulo."></textarea>


					<input type="text" name="newProducto-receta" class="form-control"
						placeholder="Nombre" style="display: none" value="">


				</div>


				<div class="col-6 my-2">
					<button type="button" class="btn btn-outline-primary"
						id="nuevaReceta-btn" data-bs-toggle="modal"
						data-bs-target="#nuevoIngrediente-producto-modal">Nuevo
						Ingrediente</button>
				</div>
				<div class="col-6 my-2">
					<input type="number" step=".01" min="0" max="500000" required value= "0"
						class="form-control st-input" name="newProducto-costo"
						id="newProducto-costo-input" placeholder="Costo" disabled />
				</div>
				<div class="col-12 my-2">
					<table class="table table-dark table-hover"
						id="listaDeIngredientes-producto-table">
						<thead>
							<tr>
								<th scope="col">#ID</th>
								<th scope="col">Nombre</th>
								<th scope="col">Costo</th>
								<th scope="col">Cantidad</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody id="newProducto-receta-tbody">

						</tbody>
					</table>
				</div>

			</div>

		</div>

	</div>
</div>

