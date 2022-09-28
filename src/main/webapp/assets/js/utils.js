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