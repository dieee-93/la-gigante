class AnimationHandler {
  switchScreen(act, newAct) {
    console.log(act, newAct);
    $(act).fadeToggle("slow", () => {
      //OCULTA EL ACTIVO, Y MUESTRA EL NUEVO ACTIVO
      act.classList.toggle("sw-act");
      $(newAct).fadeToggle("fast");
      newAct.classList.toggle("sw-act");
    });
  }

  anisap(container) {
    gsap.to(container, {
      duration: 3,
      opacity: 0,
      rotate: 360,
      yPercent:"+=400",
      ease: Elastic.easeIn,
      display: "none",
    });
   
  }

  toggleScreen(act) {
    // MUESTRA U OCULTA LA PANTALLA SEGUN SEA SU ESTADO ACTUAL
    $(act).fadeToggle("fast", () => {
      act.classList.toggle("sw-act");
    });
  }
}

class FadeControl {
  constructor(container) {
    console.log(container);
    this._mainContainer = container;
    if (
      this.mainContainer != null || //REVISA QUE EL DIV PASADO EN EL CONSTRUCTOR NO SEA NULL Y CONTENGA LA CLASE CONTAINER ANTES DE SEGUIR CONSTRUYENDO
      this.mainContainer.classList.contains("switchable")
    ) {
      this._animatron = new AnimationHandler(); //INSTANCIA UNA CLASE DEL ANIMADOR PARA USARLA COMO ENCARGADA DE ADMINISTRAR LOS CONTAINERS HIJOS
      //RECORRE SUS HIJOS PARA BUSCAR LA CLASE "sw-home"
      for (const element of this._mainContainer.children) {
        if (element.classList.contains("sw-home")) {
          element.classList.toggle("sw-act");
          this._active = element; // ASIGNA EL ELEMENTO AL ATRIBUTO ACTIVE
          this._home = element; //ASIGNA EL ELEMENTO AL ATRIBUTO HOME
        }
      }
      if (this._home === undefined && this._mainContainer.children.length > 0) {
        // EN CASO DE NO ENCONTRAR NINGUNO ASIGNA EL PRIMER HIJO DEL CONTENEDOR COMO HOME
        this._active = this._mainContainer.children[0];
        this._home = this._mainContainer.children[0];
      }
    }
    this._active.style.display = "block";
  }
  get mainContainer() {
    return this._mainContainer;
  }
  get home() {
    return this._home;
  }
  get active() {
    return this._active;
  }

  esHijo(screen) {
    let res = null;
    for (const element of this.mainContainer.children) {
      if (element.id == screen.id) {
        res = element;
      }
    }
    return res != null;
  }

  switchScreen(newActive) {
    console.log(newActive);
    if (this.esHijo(newActive)) {
      console.log(newActive);
      if (this._active == newActive) {
        this._animatron.switchScreen(this._home);
      } else {
        this._animatron.switchScreen(this._active, newActive);
        this._active = newActive;

        console.log("2");
      }
    }
  }
  returnHome() {
    this._animatron.switchScreen(this.active, this.home);
  }
}

function mostrarFormulario(id) {
  if (document.getElementById(id).style.display == "none") {
    document.getElementById(id).style.display = "block";
    if (id == "newCategory-form") {
      boton.innerText = "-";
      boton.classList.replace("btn-success", "btn-danger");
    }
  } else {
    document.getElementById(id).style.display = "none";
    if (id == "newCategory-form") {
      boton.innerText = "+";
      boton.classList.replace("btn-danger", "btn-success");
    }
  }
}
