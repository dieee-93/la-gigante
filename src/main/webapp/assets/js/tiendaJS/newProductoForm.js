$(document).ready(function() {

	let catTree = document.querySelector("#treeview");

	catTree.addEventListener("click", (e) => {
		if (e.target.classList.contains("list-group-item")) {
			console.log(e.target.innerText);
		}
	});
});


let datatable = $("#listaDeIngredientes-producto-table").DataTable({
	searching: false,
	paging: false,
});
// ELEMENTOS DEL FORM NEWPRODUCTO

let nuevoProductoBtn = document.querySelector('[name="nuevoProductoBtn"]');

let listaDeIngredientes = document.querySelector(
	"#listaDeIngredientes-producto-table"
);

let recetaInput = document.querySelector('[name="newProducto-receta"]');

let productoCosto = document.querySelector('[name="newProducto-costo"]');

// ELEMENTOS DEL MODAL

let ingredienteSelect = document.querySelector(
	"#newProducto-ingrediente-select"
);
let ingredienteNombre = document.querySelector(
	'[name="newIngrediente-nombre"]'
);
let ingredienteCategoria = document.querySelector(
	'[name="newIngrediente-categoria"]'
);
let ingredienteTipo = document.querySelector('[name="newIngrediente-tipo"]');
let ingredienteCosto = document.querySelector('[name="newIngrediente-costo"]');

let ingredienteCantidad = document.querySelector(
	'[name="newIngrediente-cantidad"]'
);
let ingredienteUnidadDeMedida = document.querySelector(
	'[name="newIngrediente-unidadDeMedida"]'
);

let agregarARecetaBtn = document.querySelector('[name="agregarAReceta-btn"]');


function capitalize(word) {
	const loweredCase = word.toLowerCase();
	return word[0].toUpperCase() + loweredCase.slice(1);
}

function mostrarCategoria(catId) {
	let catDivIdString = "cat-" + catId;
	productoShow.switchScreen(document.getElementById("productoCards"));
	productoCards.switchScreen(document.getElementById(catDivIdString));
}

function getCategoriaMateriaNombre(catId) {
	let res;
	if (catId === 0) {
		res = "Categoria principal"
	} else {
		categoriasMateria.forEach((cat) => {
			if (cat.id = catId)
				res = cat.nombre;
		})
	}

	return res;
}




class gestorDeIngredientes {


	static buscadorDeMateria(materiaNombre) {
		let res;
		materia.forEach((mat) => {
			if (mat.nombre == materiaNombre) {
				res = mat;
			}
		});
		return res;
	}


	// DEVUELVE LA SUMA DE TODOS LOS COSTOS DE LA TABLA RECETA
	static matElaboradaCostoTotal() {
		let res = 0;
		for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
			res += parseInt(listaDeIngredientes.rows[i].cells[2].textContent);
		}
		return res;
	}

	static buscadorDeProductos(idProducto) {
		let res;
		erasmo.forEach((e) => {
			if (idProducto === e.id)
				res = e;
		})
		return res;
	}


	static ingredientesDelProductoToTable(idproducto) {
		let producto = this.buscadorDeProductos(idproducto);
		let res = document.createElement('table'); //LA TABLA QUE VA A SER EL RETORNO DE LA FUNCION
		res.classList.add('table'); //CLASE DE BOOTSTRAP
		res.classList.add("ingrediente-list"); // CLASE PARA ESTILIZAR EN CSS
		
		const tableHead = document.createElement('tr'); //CREA LA CABEZERA DE LA TABLA
		let idColumn = document.createElement('th').textContent = "#ID";
		let nombreColumn = document.createElement('th').textContent = "Nombre";
		let cantidadColumn = document.createElement('th').textContent = "Cantidad";  // ACA LAS RESPECTIVAS COLUMNAS
		let costoColumn = document.createElement('th').textContent = "Costo";
		let actionColumn = document.createElement('th').textContent = "";
		
		
		
		tableHead.append(idColumn, nombreColumn, cantidadColumn, costoColumn, actionColumn); //AGREGA LAS COLUMNAS CREADAS A LA CABEZERA
		res.appendChild(tableHead) //AGREGA LA CABEZERA DE LA TABLA AL CUERPO
	
		
		producto.ingredientes.forEach((ing) => {
			
			const ingredienteTableRow = document.createElement('tr'); //ACA SE CREA UNA FILA DE LA TABLA POR CADA INGREDIENTE DEL PRODUCTO
			
			 // ACA SE LE ASIGNAN LOS VALORES DE CADA INGREDIENTE A LAS COLUMNAS DE LA TABLA	
			const ingredienteIdColumn = document.createElement('td').textContent = ing.id;
			ingredienteIdColumn.setAttribute('scope', 'row'); //ATRIBUTO PARA BOOSTRAP
			const ingredienteNombreColumn = document.createElement('td').textContent = ing.nombre;
			const ingredienteCantidadColumn = document.createElement('td').textContent = ing.cantidad
			const ingredienteCostoColumn = document.createElement('td').textContent = ing.costo + "$";
			
			// ACA SE CREA LA COLUMNA DE ACCION DE CADA FILA CON SUS BOTONES DE EDITAR/ELIMINAR 
			const ingredienteActionColumn = document.createElement('td');
			const ingredienteBtnEditar = document.createElement('button');
			const ingredienteBtnEliminar = document.createElement('button');
			
			ingredienteActionColumn.append(ingredienteBtnEditar, ingredienteBtnEliminar);
			
			
			// ACA SE AGREGAN LAS CELDAS CREADAS PARA CADA INGREDIENTE A LA FILA CORRESPONDIENTE
			ingredienteTableRow.append(ingredienteIdColumn, ingredienteNombreColumn, ingredienteCantidadColumn, ingredienteCostoColumn, ingredienteActionColumn);
			
			//FINALMENTE SE AGREGA LA ROW TERMINADA A LA TABLA QUE SERA RETORNADA
			res.appendChild(ingredienteTableRow);
			
			ingredienteIdColumn.setAttribute('scope', 'row'); //ATRIBUTO PARA BOOSTRAP
		})
		return res;
	}



	static constructorDeRecetas() {
		let receta = "";
		// ESTOS CARACTERES SON NECESARIOS PARA CREAR LA CADENA DE INGREDIENTES
		const separadorDeParametros = "-"; //SEPARA LOS PARAMETROS
		const separadorDeIngredientes = "/"; //SEPARA LOS INGREDIENTES
		for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
			//RECORRE LA LISTA DE INGREDIENTES
			let idIngrediente = listaDeIngredientes.rows[i].cells[0].textContent;
			let cantidadIngrediente = "";
			let unidadDeMedidaIngrediente = "";
			if (
				listaDeIngredientes.rows[i].cells[3].textContent.split(" ")[1] ==
				"Unidad" || //REVISA COMO SE CONTABILIZA LA MATERIA
				listaDeIngredientes.rows[i].cells[3].textContent.split(" ")[1] ==
				"Unidades"
			) {
				// CASO ELABORADA
				cantidadIngrediente =
					listaDeIngredientes.rows[i].cells[3].textContent.split(" ")[0];

				let stringFinal =
					idIngrediente +
					separadorDeParametros + //CONSTRUYE UNA CADENA DE STRINGS FORMATO:
					cantidadIngrediente + // "IDMATERIA + separadorDeParametros + CANTIDAD + separadorDeIngredientes"
					separadorDeIngredientes;

				receta += stringFinal;
			} else {
				//CASO CONTABLE
				cantidadIngrediente =
					listaDeIngredientes.rows[i].cells[3].textContent.split(" ")[0];
				unidadDeMedidaIngrediente =
					listaDeIngredientes.rows[i].cells[3].textContent.split(" ")[1];

				let stringFinal =
					idIngrediente +
					separadorDeParametros +
					cantidadIngrediente + //CONSTRUYE UNA CADENA DE STRINGS FORMATO:
					separadorDeParametros + //"IDMATERIA + separadorDeParametros + CANTIDAD + separadorDeParametros + UNIDADDEMEDIDA + separadorDeIngredientes"
					unidadDeMedidaIngrediente +
					separadorDeIngredientes;

				receta += stringFinal;
			}
		}
		recetaInput.value = receta; // ASIGNA EL VALOR AL INPUT OCULTO EN EL FORM
	}

	static calculadoraDeCostos() {
		let mat = this.buscadorDeMateria(ingredienteNombre.value);
		//REVISA SI ES CONMESURABLE
		if (mat.tipo == "conmesurable") {
			//REVISA LA UNIDAD DE MEDIDA SELECCIONADA PARA SABER LA CONVERSION
			if (
				ingredienteUnidadDeMedida.value == "kg" ||
				ingredienteUnidadDeMedida.value == "l"
			) {
				ingredienteCosto.value =
					mat.precioMinimo * ingredienteCantidad.value * 1000;
			}
			if (
				ingredienteUnidadDeMedida.value == "gr" ||
				ingredienteUnidadDeMedida.value == "ml"
			) {
				ingredienteCosto.value = mat.precioMinimo * ingredienteCantidad.value;
			}
		}
		//REVISA SI ES CONTABLE
		else if (mat.tipo == "contable") {
			ingredienteCosto.value = ingredienteCantidad.value * mat.precioMinimo;
		}
	}

	static matElaboradaCostoTotal() {
		let res = 0;
		for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
			res += parseInt(listaDeIngredientes.rows[i].cells[2].textContent);
		}
		return res;
	}


	static mostrarIngrediente() {
		let articuloSeleccionado = this.buscadorDeMateria(
			ingredienteSelect.options[ingredienteSelect.selectedIndex].text
		);

		if (ingredienteSelect.selectedIndex != ingredienteSelect.options.length - 1) {
			ingredienteNombre.value = articuloSeleccionado.nombre;

			ingredienteCategoria.value = getCategoriaMateriaNombre(articuloSeleccionado.categoriaId);

			ingredienteTipo.value = capitalize(articuloSeleccionado.tipo);

			switch (ingredienteTipo.value) {
				case "Conmesurable":
					ingredienteUnidadDeMedida.style.display = "block";
					ingredienteCantidad.placeholder = "Cantidad";
					break;
				case "Contable":
					ingredienteUnidadDeMedida.style.display = "none";
					ingredienteCantidad.placeholder = "Unidad/es";
					break;
			}
		} else {
			ingredienteNombre.value = "";
			ingredienteCategoria.value = "";
			ingredienteTipo.value = "";
		}
	}

	static ingredienteYaExiste(nombre) {
		let res = false;
		// RECORRE LA TABLA DE INGREDIENTES
		if (listaDeIngredientes.rows.length > 2) {
			for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
				//SI ENCUENTRA UNA COINCIDENCIA EN LA CELDA DE NOMBRE DEVUELVE TRUE
				if (nombre == listaDeIngredientes.rows[i].cells[1].textContent) {
					res = true;
				}
			}
		}
		return res;
	}

	static modificarCantidad(nombre, cantidadAModificar, operacion) {
		//TODO FALTA AGREGAR LA LOGICA SUMA Y RESTA
		// RECORRE LA TABLA DE INGREDIENTES
		for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
			//DEVUELVE EL VALOR DE LA COLUMNA CANTIDAD PARSEADO A ENTERO
			if (nombre == listaDeIngredientes.rows[i].cells[1].textContent) {
				const txt = listaDeIngredientes.rows[i].cells[3].innerText.split(" ")[1];
				listaDeIngredientes.rows[i].cells[3].textContent =
					parseInt(listaDeIngredientes.rows[i].cells[3].innerText.split(" ")[0]) +
					parseInt(cantidadAModificar) +
					" " +
					txt;
			}
		}
	}

	static agregarIngrediente() {
		//REVISA QUE SE HAYA SELECCIONADO UNA ACCION Y QUE LOS INPUT ESTEN COMPLETOS
		if (
			ingredienteSelect.selectedIndex != ingredienteSelect.options.length - 1 &&
			ingredienteCantidad.value != ""
		) {
			if (this.ingredienteYaExiste(ingredienteNombre.value)) {
				this.modificarCantidad(
					ingredienteNombre.value,
					ingredienteCantidad.value,
					"+"
				);
				this.modificarCosto(ingredienteNombre.value, ingredienteCosto.value, "+");
			} else {
				let cantidadToString;
				const mat = this.buscadorDeMateria(ingredienteNombre.value);

				if (ingredienteTipo.value == "Conmesurable") {
					cantidadToString =
						ingredienteCantidad.value + " " + ingredienteUnidadDeMedida.value;
				} else if (ingredienteTipo.value == "Contable") {
					cantidadToString =
						ingredienteCantidad.value == 1
							? ingredienteCantidad.value + " Unidad"
							: ingredienteCantidad.value + " Unidades";
				}

				let actionButtons = `<button type="button" class="btn btn-info" ><i class="fa-solid fa-pencil"></i></button><button type="button" class="btn btn-info" ><i class="fa-solid fa-trash"></i></button>`;

				let tmpArray = [
					mat.id,
					mat.nombre,
					ingredienteCosto.value,
					cantidadToString,
					actionButtons,
				];

				datatable.row
					.add(tmpArray)
					.draw()
					.column(4)
					.nodes()
					.to$()
					.addClass("actionCenter");
			}
		}
		productoCosto.value = this.matElaboradaCostoTotal();
	}


}





$("#newProducto-form").on("submit", function() {
	constructorDeRecetas();
	productoCosto.disabled = false;
	return true;
});

// INICIALIZACION CONTAINER SWITCHEABLES

//PRODUCTO-SHOW
const productoShow = new Switcheable(
	document.getElementById("producto-show-div")
);

//PRODUCTO-CARDS
const productoCards = new Switcheable(document.getElementById("productoCards"));

//INGREDIENTE-INFO 

const ingredienteInfo = new Switcheable(document.getElementById("nuevoIngrediente-info"));

//EVENT LISTENERS

nuevoProductoBtn.addEventListener("click", () => {
	productoShow.switchScreen(nuevoProductoForm);
});

ingredienteSelect.addEventListener("change", () => {
	if (ingredienteSelect.selectedOptions[0].parentElement.label === "Materias Primas") {
		
		let agregarIngredienteForm = document.getElementById("agregarIngrediente-form");
		gestorDeIngredientes.mostrarIngrediente();
		gestorDeIngredientes.calculadoraDeCostos();
		if(!agregarIngredienteForm.classList.contains("sw-act")){
		ingredienteInfo.switchScreen(agregarIngredienteForm);
		}
	
	}
	if (ingredienteSelect.selectedOptions[0].parentElement.label === "Productos") {
		let agregarIngredienteList = document.getElementById("agregarIngrediente-list");
		
		if(agregarIngredienteList.hasChildNodes()){
				agregarIngredienteList.childNodes[0].remove();
		}
	
		agregarIngredienteList.appendChild(gestorDeIngredientes.ingredientesDelProductoToTable(parseInt(ingredienteSelect.selectedOptions[0].value))) 
	
		if(!agregarIngredienteList.classList.contains("sw-act")){
		ingredienteInfo.switchScreen(agregarIngredienteList);
		}
	}

});

agregarARecetaBtn.addEventListener("click", () => {
	gestorDeIngredientes.agregarIngrediente();
});

ingredienteCantidad.addEventListener("change", () => {
	gestorDeIngredientes.calculadoraDeCostos();
});

ingredienteUnidadDeMedida.addEventListener("change", () => {
	gestorDeIngredientes.calculadoraDeCostos();
});
