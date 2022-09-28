<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container g-0" id="producto-form">
	<div class="row">
		<div class="container-fluid">

			<div class="row">
				<div class="col-12 mb-2 px-0">
					<input type="text" name="newProducto-nombre"
						id="newProducto-nombre-input" class="form-control"
						placeholder="Nombre del Producto" />
				</div>
				<div class="col-12 px-0">

					<textarea class="form-control" name="newProducto-descripcion"
						id="newProducto-descripcion-input" rows="4"
						placeholder="Inserte una breve descripcion del articulo."></textarea>
						
						
			<input type="text" name="newProducto-receta" class="form-control"
				placeholder="Nombre" style="display:none" value="">


				</div>


				<div class="col-6 my-2 px-0">
					<button type="button" class="btn btn-outline-primary"
						id="nuevaReceta-btn" data-bs-toggle="modal"
						data-bs-target="#nuevoIngrediente-producto-modal">Nuevo
						Ingrediente</button>
				</div>
				<div class="col-6 my-2 px-0">
					<input type="number" step=".01" min="0" max="500000" required
						class="form-control" name="newProducto-costo"
						id="newProducto-costo-input" placeholder="Costo" disabled />
				</div>
				<div class="col-12 my-2 px-0">
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

<div class="modal fade" id="nuevoIngrediente-producto-modal"
	tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="nuevoIngrediente-producto-modalLabel">Agrega
					un ingrediente</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="cointainer g-0">
					<div class="row">
						<div class="col-6">
							<p>Selecciona uno de tu lista de materias primas.</p>
							<p>Si algún ingrediente no se encuentra en Stock, el articulo
								no se mostrar al publico.</p>
							<p>Tambien puedes seleccionar otro producto como ingrediente,
								al hacerlo todos los ingredientes de ese producto se agregaran a
								la lista.</p>
							<select class="selectpicker" data-style="btn-success"
								title="Elige un ingrediente" data-live-search="true"
								id="newProducto-ingrediente-select">
								<c:if test="${!listaDeMateria.isEmpty()}">
									<optgroup label="Materias Primas">
										<c:forEach items="${listaDeMateria}" var="mat">
											<option value="${mat.id}">${mat.nombre}</option>
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
						<div class="col-6">
							<div class="container my-1 g-0 mt-4 mb-0">

								<div class="row">
									<div class="col-6">
										<input type="text" name="newIngrediente-nombre"
											 class="form-control"
											placeholder="Nombre" disabled />
									</div>
									<div class="col-6">
										<input type="text" name="newIngrediente-categoria"
											class="form-control"
											placeholder="Categoria" disabled />
									</div>


									<div class="col-6 my-2">
										<input type="text" name="newIngrediente-tipo"
											 class="form-control"
											placeholder="Tipo" disabled />
									</div>
									<div class="col-6 my-2">
										<input type="text" name="newIngrediente-costo"
											class="form-control"
											placeholder="Costo" disabled />
									</div>

								</div>


							</div>

							<div class="container g-0 mb-3" id="materiaCantidad-form">

								<div class="row">
									<div class="col-12">


										<input type="number" min="0" max="20000"
											name="newIngrediente-cantidad"
										    class="form-control"
											placeholder="Cantidad" /> <select class="form-select"
											name="newIngrediente-unidadDeMedida"
											style="display: none">
											<option value="kg">Kg</option>
											<option value="gr">g</option>
											<option value="l">L</option>
											<option value="ml">ml</option>
										</select>

									</div>
									<div
										class="col-6 d-flex justify-content-center align-items-center">


									</div>
								</div>


							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-primary"
					name="agregarAReceta-btn">Agregar</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Finalizar</button>
			</div>
		</div>
	</div>
</div>
