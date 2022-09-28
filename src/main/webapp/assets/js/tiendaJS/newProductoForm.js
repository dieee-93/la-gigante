$(document).ready(function () {
  // INICIALIZACION DE LA DATATABLE
   let datatable = $("#listaDeIngredientes-producto-table").DataTable({
    searching: false,
    lengthChange: false,
  });



  // ELEMENTOS DEL FORM NEWPRODUCTO

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
  let ingredienteCosto = document.querySelector(
    '[name="newIngrediente-costo"]'
  );

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

  function buscadorDeMateria(materiaNombre) {
    let res;
    materia.forEach((mat) => {
      if (mat.nombre == materiaNombre) {
        res = mat;
      }
    });
    return res;
  }

  function constructorDeRecetas() {
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

  function calculadoraDeCostos() {
    mat = buscadorDeMateria(ingredienteNombre.value);
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

  function matElaboradaCostoTotal() {
    let res = 0;
    for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
      res += parseInt(listaDeIngredientes.rows[i].cells[2].textContent);
    }
    return res;
  }

  function mostrarIngrediente() {
    let articuloSeleccionado = buscadorDeMateria(
      ingredienteSelect.options[ingredienteSelect.selectedIndex].text
    );
    console.log(articuloSeleccionado.categoria);

    if (
      ingredienteSelect.selectedIndex !=
      ingredienteSelect.options.length - 1
    ) {
      ingredienteNombre.value = articuloSeleccionado.nombre;

      ingredienteCategoria.value = articuloSeleccionado.categoria;

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

  function ingredienteYaExiste(nombre) {
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

  function modificarCantidad(nombre, cantidadAModificar, operacion) {
    //TODO FALTA AGREGAR LA LOGICA SUMA Y RESTA
    // RECORRE LA TABLA DE INGREDIENTES
    for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
      //DEVUELVE EL VALOR DE LA COLUMNA CANTIDAD PARSEADO A ENTERO
      if (nombre == listaDeIngredientes.rows[i].cells[1].textContent) {
        const txt =
          listaDeIngredientes.rows[i].cells[3].innerText.split(" ")[1];
        listaDeIngredientes.rows[i].cells[3].textContent =
          parseInt(
            listaDeIngredientes.rows[i].cells[3].innerText.split(" ")[0]
          ) +
          parseInt(cantidadAModificar) +
          " " +
          txt;
      }
    }
  }

  function agregarIngrediente() {
    //REVISA QUE SE HAYA SELECCIONADO UNA ACCION Y QUE LOS INPUT ESTEN COMPLETOS
    if (
      ingredienteSelect.selectedIndex != ingredienteSelect.options.length - 1 &&
      ingredienteCantidad.value != ""
    ) {
      if (ingredienteYaExiste(ingredienteNombre.value)) {
        modificarCantidad(
          ingredienteNombre.value,
          ingredienteCantidad.value,
          "+"
        );
        modificarCosto(ingredienteNombre.value, ingredienteCosto.value, "+");
      } else {
        let cantidadToString;
        const mat = buscadorDeMateria(ingredienteNombre.value);

        if (ingredienteTipo.value == "Conmesurable") {
          cantidadToString =
            ingredienteCantidad.value + " " + ingredienteUnidadDeMedida.value;
        } else if (ingredienteTipo.value == "Contable") {
          cantidadToString =
            ingredienteCantidad.value == 1
              ? ingredienteCantidad.value + " Unidad"
              : ingredienteCantidad.value + " Unidades";
        }

	   	let actionButtons = '<button type="button" class="btn btn-info" ><i class="fa-solid fa-pencil"></i></button>' + '<button type="button" class="btn btn-info" ><i class="fa-solid fa-trash"></i></button>';
        
  		let tmpArray = [mat.id, mat.nombre, ingredienteCosto.value, cantidadToString, actionButtons];
  		
  		
  		datatable.row.add(tmpArray).draw().column(4).nodes().to$().addClass('actionCenter'); 
      }
    }
    productoCosto.value = matElaboradaCostoTotal();
  }

  // DEVUELVE LA SUMA DE TODOS LOS COSTOS DE LA TABLA RECETA
  function matElaboradaCostoTotal() {
    let res = 0;
    for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
      res += parseInt(listaDeIngredientes.rows[i].cells[2].textContent);
    }
    return res;
  }

  $("#newProducto-form").on("submit", function () {
    constructorDeRecetas();
    productoCosto.disabled = false;
    return true;
  });

  //EVENT LISTENERS
  ingredienteSelect.addEventListener("change", () => {
    mostrarIngrediente();
  });

  agregarARecetaBtn.addEventListener("click", () => {
    agregarIngrediente();
  });

  ingredienteCantidad.addEventListener("change", () => {
    calculadoraDeCostos();
  });
  ingredienteSelect.addEventListener("change", () => {
    calculadoraDeCostos();
  });

  ingredienteUnidadDeMedida.addEventListener("change", () => {
    calculadoraDeCostos();
  });
});