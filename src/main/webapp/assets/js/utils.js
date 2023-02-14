class Animatron {
	
	static Animation() {
		this.currentAnimation = gsap.timeline();
	}

	static showFromBottom(target) {
		this.currentAnimation =	gsap.timeline().to(target, {
			yPercent: "+=100",
		}).to(target, {
			opacity: 100,
			yPercent: "-=100",
			display: "block",
		});


	}


	static showFromRight(target) {

	this.currentAnimation = gsap.timeline().to(target, {

			xPercent: "+=100",

		}).to(target, {

			xPercent: "-=100",
			delay: 0.1,
			duration: 0.5,
			opacity: 100,
			display: "block",

		});

	}

	static switchToRight(target, target2) {
		this.currentAnimation = gsap.timeline().to(target, {
			duration: 0.5,
			opacity: 0,
			xPercent: "+=100",
			display: "none",
		}).to(target, {

			xPercent: "-=100",
			onComplete: this.showFromBottom(target2),

		});


	}
	static toggleScreen(act) {
		// MUESTRA U OCULTA LA PANTALLA SEGUN SEA SU ESTADO ACTUAL
		$(act).fadeToggle("fast", () => {
			act.classList.toggle("sw-act");
		});
	}
}

// CLASE SWITCHEABLE
// CUALQUIER DIV INICIALIZADO COMO SWITCHEABLE, PUEDE INTERCAMBIAR DE PANTALLAS CON ANIMACIONES EJECUTANDO LAS FUNCIONES DE CLASE switchScreen()
// EL DIV DEBE TENER LA CLASE SWITCHEABLE ASIGNADA Y ALGUN HIJO DE ESTE DIV, LA CLASE "sw-home", PARA ASIGNARLA COMO PANTALLA DE INICIO.
class Switcheable {
	
	constructor(container) { //RECIBE COMO PARAMETRO UN DIV QUE QUIERA TRANSFORMARSE EN SWITCHEABLE 
		console.log(container);
		this._screen = container;
		if (
			this._screen != null || //REVISA QUE EL DIV PASADO EN EL CONSTRUCTOR NO SEA NULL Y CONTENGA LA CLASE CONTAINER ANTES DE SEGUIR CONSTRUYENDO
			this._screen.classList.contains("switchable")
			
		) {
			Animatron.Animation();
			//RECORRE SUS HIJOS PARA BUSCAR LA CLASE "sw-home"
			for (const element of this._screen.children) {
				if (element.classList.contains("sw-home")) {
					element.classList.toggle("sw-act");
					this._active = element; // ASIGNA EL ELEMENTO AL ATRIBUTO DE CLASE ACTIVE
					this._home = element; //ASIGNA EL ELEMENTO AL ATRIBUTO DE CLASE HOME
				}
			}
			if (this._home === undefined && this._screen.children.length > 0) {
				// EN CASO DE NO ENCONTRAR NINGUNO ASIGNA EL PRIMER HIJO DEL CONTENEDOR COMO HOME
				this._active = this._screen.children[0];
				this._home = this._screen.children[0];
			}
		}
		this._active.style.display = "block";
	}
	get screen() {
		return this._screen;
	}
	get home() {
		return this._home;
	}
	get active() {
		return this._active;
	}

	esHijo(screen) {
		let res = null;
		for (const element of this.screen.children) {
			if (element.id == screen.id) {
				res = element;
			}
		}
		return res != null;
	}

	switchScreen(newActive) {
		if (this.esHijo(newActive)) {
			if (this._active == newActive && !Animatron.currentAnimation.isActive() ) {
			
				this.returnHome();
			} else {
				if (!Animatron.currentAnimation.isActive()){	
				Animatron.switchToRight(this._active, newActive);
				this._active.classList.toggle("sw-act");
				newActive.classList.toggle("sw-act");
				this._active = newActive;

				}
			
			
			}
		}
	}
	returnHome() {
		Animatron.switchToRight(this._active, this._home);
		this._active = this._home;
	}
}

function mostrarFormulario(id) {
	if (document.getElementById(id).style.display == "none") {
		document.getElementById(id).style.display = "block";
		if (id == "newCategory-form") {
			
			boton.classList.replace("btn-success", "btn-danger");
		}
	} else {
		document.getElementById(id).style.display = "none";
		if (id == "newCategory-form") {
			boton.classList.replace("btn-danger", "btn-success");
		}
	}
}
