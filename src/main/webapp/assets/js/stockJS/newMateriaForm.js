$(document).ready(() => {
  // ELEMENTOS DEL FORM NEWMATERIA-INFO
  let nuevaMateriaNombreInput = document.querySelector(
    "#newMateria-nombre-input"
  );
  let nuevaMateriaTipoSelectInput = document.querySelector(
    "#newMateria-tipo-select"
  );
  let nuevaMateriaCostoInput = document.querySelector(
    "#newMateria-costo-input"
  );

  // ELEMENTOS DEL FORM NEWMATERIA-CANTIDAD
  let nuevaMateriaCantidadForm = document.querySelector(
    "#materiaCantidad-form"
  );
  let nuevaMateriaCantidadInput = document.querySelector(
    "#newMateria-cantidad"
  );

  //MATERIA-CONMESURABLE-FORM

  let nuevaMateriaUnidadDeMedidaSelect = document.querySelector(
    "#newMateria-unidadDeMedida-select"
  );

  // MATERIA-CONTABLE-FORM
  let nuevaMateriaUnitariaCheckboxDiv = document.querySelector(
    "#materiaContable-checkboxDiv"
  );
  let nuevaMateriaUnitariaCheckbox = document.querySelector(
    "#materiaUnitaria-checkbox"
  );

  // MATERIA-ELABORADA-FORM
  let materiaElaboradaForm = document.querySelector("#materiaElaborada-form");
  let listaDeIngredientes = document.querySelector(
    "#listaDeIngredientes-table"
  );
  let recetaNuevaMateria = document.querySelector("#newMateria-receta-input");
  let nuevaRecetaBtn = document.querySelector("#nuevaReceta-btn");
  let agregarARecetaBtn = document.querySelector("#agregarAReceta-btn");
  let nuevoIngredienteSelect = document.querySelector("#newIngrediente-select");
  let nuevoIngredienteNombre = document.querySelector(
    "#newIngrediente-nombre-input"
  );
  let nuevoIngredienteCategoria = document.querySelector(
    "#newIngrediente-categoria-input"
  );
  let nuevoIngredienteTipo = document.querySelector(
    "#newIngrediente-tipo-input"
  );
  let nuevoIngredienteCosto = document.querySelector(
    "#newIngrediente-costo-input"
  );
  let nuevoIngredienteCantidad = document.querySelector(
    "#newIngrediente-cantidad-input"
  );
  let nuevoIngredienteUnidadDeMedida = document.querySelector(
    "#newIngrediente-unidadDeMedida-select"
  );

  function capitalize(word) {
    const loweredCase = word.toLowerCase();
    return word[0].toUpperCase() + loweredCase.slice(1);
  }

  function ingredienteYaExiste(nombre) {
    let res = false;
    // RECORRE LA TABLA DE INGREDIENTES
    if (listaDeIngredientes.rows.length > 1) {
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

  function modificarCosto(nombre, cantidadAModificar, operacion) {
    //TODO FALTA AGREGAR LA LOGICA SUMA Y RESTA
    // RECORRE LA TABLA DE INGREDIENTES
    for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
      //DEVUELVE EL VALOR DE LA COLUMNA CANTIDAD PARSEADO A ENTERO
      if (nombre == listaDeIngredientes.rows[i].cells[1].textContent) {
        let costoActualizado =
          parseInt(cantidadAModificar) +
          parseInt(listaDeIngredientes.rows[i].cells[2].textContent);
        listaDeIngredientes.rows[i].cells[2].textContent = costoActualizado;
      }
    }
  }
  function agregarIngrediente() {
    //REVISA QUE SE HAYA SELECCIONADO UNA ACCION Y QUE LOS INPUT ESTEN COMPLETOS
    if (
      nuevoIngredienteSelect.selectedIndex !=
        nuevoIngredienteSelect.options.length - 1 &&
      nuevoIngredienteCantidad.value != ""
    ) {
      if (ingredienteYaExiste(nuevoIngredienteNombre.value)) {
        modificarCantidad(
          nuevoIngredienteNombre.value,
          nuevoIngredienteCantidad.value,
          "+"
        );
        modificarCosto(
          nuevoIngredienteNombre.value,
          nuevoIngredienteCosto.value,
          "+"
        );
      } else {
        let row = listaDeIngredientes.insertRow();
        let cellId = (row.insertCell().innerHTML =
          erasmo[
            nuevoIngredienteSelect.options[
              nuevoIngredienteSelect.selectedIndex
            ].text
          ].id);
        let cellNombre = (row.insertCell().innerHTML =
          nuevoIngredienteNombre.value);
        let cellCosto = (row.insertCell().innerHTML =
          nuevoIngredienteCosto.value);
        if (nuevoIngredienteTipo.value == "Conmesurable") {
          let cantidadToString =
            nuevoIngredienteCantidad.value +
            " " +
            nuevoIngredienteUnidadDeMedida.value;
          let cellCantidad = (row.insertCell().innerHTML = cantidadToString);
        } else if (nuevoIngredienteTipo.value == "Contable") {
          let cantidadToString =
            nuevoIngredienteCantidad.value == 1
              ? nuevoIngredienteCantidad.value + " Unidad"
              : nuevoIngredienteCantidad.value + " Unidades";
          let cellCantidad = (row.insertCell().innerHTML = cantidadToString);
        }
      }
    }
    nuevaMateriaCostoInput.value = matElaboradaCostoTotal();
  }
  // DEVUELVE LA SUMA DE TODOS LOS COSTOS DE LA TABLA RECETA
  function matElaboradaCostoTotal() {
    let res = 0;
    for (let i = 1; i < listaDeIngredientes.rows.length; i++) {
      res += parseInt(listaDeIngredientes.rows[i].cells[2].textContent);
    }
    return res;
  }
  function calculadoraDeCostos() {
    //REVISA SI ES CONMESURABLE
    if (erasmo[nuevoIngredienteNombre.value].tipo == "conmesurable") {
      //REVISA LA UNIDAD DE MEDIDA SELECCIONADA PARA SABER LA CONVERSION
      if (
        nuevoIngredienteUnidadDeMedida.value == "kg" ||
        nuevoIngredienteUnidadDeMedida.value == "l"
      ) {
        nuevoIngredienteCosto.value =
          erasmo[nuevoIngredienteNombre.value].precioMinimo *
          nuevoIngredienteCantidad.value *
          1000;
      }
      if (
        nuevoIngredienteUnidadDeMedida.value == "gr" ||
        nuevoIngredienteUnidadDeMedida.value == "ml"
      ) {
        nuevoIngredienteCosto.value =
          erasmo[nuevoIngredienteNombre.value].precioMinimo *
          nuevoIngredienteCantidad.value;
      }
    }
    //REVISA SI ES CONTABLE
    else if (erasmo[nuevoIngredienteNombre.value].tipo == "contable") {
      nuevoIngredienteCosto.value =
        nuevoIngredienteCantidad.value *
        erasmo[nuevoIngredienteNombre.value].precioUnitario;
    }
  }

  function mostrarIngrediente() {
    if (
      nuevoIngredienteSelect.selectedIndex !=
      nuevoIngredienteSelect.options.length - 1
    ) {
      nuevoIngredienteNombre.value =
        nuevoIngredienteSelect.options[
          nuevoIngredienteSelect.selectedIndex
        ].text;
      nuevoIngredienteCategoria.value =
        erasmo[
          nuevoIngredienteSelect.options[
            nuevoIngredienteSelect.selectedIndex
          ].text
        ].categoria;
      nuevoIngredienteTipo.value = capitalize(
        erasmo[
          nuevoIngredienteSelect.options[nuevoIngredienteSelect.selectedIndex]
            .text
        ].tipo
      );

      switch (nuevoIngredienteTipo.value) {
        case "Conmesurable":
          nuevoIngredienteUnidadDeMedida.style.display = "block";
          nuevoIngredienteCantidad.placeholder = "Cantidad";
          break;
        case "Contable":
          nuevoIngredienteUnidadDeMedida.style.display = "none";
          nuevoIngredienteCantidad.placeholder = "Unidad/es";
          break;
      }
    } else {
      nuevoIngredienteNombre.value = "";
      nuevoIngredienteCategoria.value = "";
      nuevoIngredienteTipo.value = "";
    }
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
    recetaNuevaMateria.value = receta; // ASIGNA EL VALOR AL INPUT OCULTO EN EL FORM
  }

  agregarARecetaBtn.addEventListener("click", () => {
    agregarIngrediente();
  });
  nuevoIngredienteSelect.addEventListener("change", () => {
    mostrarIngrediente();
  });

  nuevoIngredienteCantidad.addEventListener("change", () => {
    calculadoraDeCostos();
  });
  nuevoIngredienteSelect.addEventListener("change", () => {
    calculadoraDeCostos();
  });

  nuevoIngredienteUnidadDeMedida.addEventListener("change", () => {
    calculadoraDeCostos();
  });

  function reiniciarFormularios() {
    nuevaMateriaUnitariaCheckboxDiv.style.display = "none";
    nuevaMateriaUnidadDeMedidaSelect.style.display = "none";
    nuevaMateriaCostoInput.style.display = "none";
    nuevaRecetaBtn.style.display = "none";
    materiaElaboradaForm.style.display = "none";
    nuevaMateriaCostoInput.placeholder = "Costo";
    nuevaMateriaCantidadInput.placeholder = "Cantidad";
    nuevaMateriaCostoInput.disabled = false;
  }

  reiniciarFormularios();

  $("#newMateria-form").on("submit", function() {
    if (nuevaMateriaTipoSelectInput.value == "elaborada") {
      constructorDeRecetas();
      nuevaMateriaCostoInput.disabled = false;
      console.log(nuevaMateriaCostoInput.value);
      console.log("aaaaaaaaaaaaa");
      console.log(recetaNuevaMateria.value);
    }
    return true;
  });

  nuevaMateriaUnitariaCheckbox.addEventListener("change", () => {
    if (!nuevaMateriaUnitariaCheckbox.checked) {
      nuevaMateriaCantidadInput.style.display = "block";
      nuevaMateriaCostoInput.placeholder = "Costo Total";
      nuevaMateriaCostoInput.style.display = "block";
    } else {
      nuevaMateriaCantidadInput.style.display = "none";
      nuevaMateriaCantidadInput.placeholder = "Unidades";
      nuevaMateriaCostoInput.placeholder = "Costo Unitario";
      nuevaMateriaCostoInput.style.display = "block";
    }
  });

  nuevaMateriaTipoSelectInput.addEventListener("change", () => {
    switch (nuevaMateriaTipoSelectInput.value) {
      case "conmesurable":
        reiniciarFormularios();
        nuevaMateriaCantidadInput.placeholder = "Cantidad";
        nuevaMateriaCostoInput.placeholder = "Costo Total";
        nuevaMateriaCantidadInput.style.display = "block";
        nuevaMateriaUnidadDeMedidaSelect.style.display = "block";
        nuevaMateriaCostoInput.style.display = "block";
        nuevaMateriaCantidadForm.style.display = "block";
        break;
      case "contable":
        reiniciarFormularios();

        nuevaMateriaCantidadInput.placeholder = "Unidades";
        if (nuevaMateriaUnitariaCheckbox.checked) {
          nuevaMateriaCantidadInput.style.display = "none";
          nuevaMateriaCostoInput.placeholder = "Costo Unitario";
        } else {
          nuevaMateriaCantidadInput.style.display = "block";
          nuevaMateriaCostoInput.placeholder = "Costo Total";
        }
        nuevaMateriaCostoInput.style.display = "block";
        nuevaMateriaUnitariaCheckboxDiv.style.display = "block";
        nuevaMateriaCantidadForm.style.display = "block";
        break;
      case "elaborada":
        reiniciarFormularios();
        nuevaMateriaCostoInput.disabled = true;
        nuevaMateriaCostoInput.style.display = "block";
        nuevaRecetaBtn.style.display = "block";
        materiaElaboradaForm.style.display = "block";
        nuevaMateriaCantidadForm.style.display = "block";

        break;
      case "default":
        reiniciarFormularios();
        nuevaMateriaCantidadForm.style.display = "none";
        break;
    }
  });
});
