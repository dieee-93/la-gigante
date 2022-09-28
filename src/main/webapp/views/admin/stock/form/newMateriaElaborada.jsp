<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 					 -->
<div class="container-fluid g-0" id="materiaElaborada-form">
	<div class="row">
		<div class="col-12 ">
			<table class="table table-dark table-hover"
				id="listaDeIngredientes-table">
				<thead>
					<tr>
						<th scope="col">#ID</th>
						<th scope="col">Nombre</th>
						<th scope="col">Costo</th>
						<th scope="col">Cantidad</th>
					</tr>
				</thead>
				<tbody id="materiaElaborada-receta-tbody">

				</tbody>
			</table>
		</div>
	</div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Agrega un
					ingrediente</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="cointainer g-0">
					<div class="row">
						<div class="col-6">
							<p>Seleccioná uno de tu stock</p>
							<select class="form-select" aria-label="Default select example"
								id="newIngrediente-select">
								<c:if test="${!listaDeMateria.isEmpty()}">
									<c:forEach items="${listaDeMateria}" var="mat">
										<option value="${mat.id}">${mat.nombre}</option>
									</c:forEach>
									<option selected>Open this select menu</option>
								</c:if>
							</select>

						</div>
						<div class="col-4">
							<div class="container my-1 g-0 mt-4 mb-0">

								<div class="row">
									<div class="col-6">
										<input type="text" name="newIngrediente-nombre"
											id="newIngrediente-nombre-input" class="form-control"
											placeholder="Nombre" disabled />
									</div>
									<div class="col-6">
										<input type="text" name="newIngrediente-categoria"
											id="newIngrediente-categoria-input" class="form-control"
											placeholder="Categoria" disabled />
									</div>


									<div class="col-6 my-2">
										<input type="text" name="newIngrediente-tipo"
											id="newIngrediente-tipo-input" class="form-control"
											placeholder="Tipo" disabled />
									</div>
									<div class="col-6 my-2">
										<input type="text" name="newIngrediente-costo"
											id="newIngrediente-costo-input" class="form-control"
											placeholder="Costo" disabled />
									</div>

								</div>


							</div>

							<div class="container g-0 mb-3">

								<div class="row">
									<div class="col-12">


										<input type="number" min="0" max="20000"
											name="newIngrediente-cantidad"
											id="newIngrediente-cantidad-input" class="form-control"
											placeholder="Cantidad" /> <select class="form-select"
											name="newIngrediente-unidadDeMedida"
											id="newIngrediente-unidadDeMedida-select"
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

