package model.system.materiaprima;

import java.util.HashMap;
import java.util.List;

import model.system.interfaces.Categorizable;
import model.system.interfaces.Treeable;
import utils.JavaObjectToJSON.POJO.MateriaPOJO;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public abstract class Materia implements Treeable, Categorizable {

	protected int id;
	protected String nombre;
	protected Integer categoriaId;
	protected String tipo;
	protected Double costo;
	protected Double cantidad;

	protected HashMap<String, String> errors;

	protected Materia(Integer id, String nombre, Integer categoriaId, String tipo, Double costo, Double cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.categoriaId = categoriaId;
		this.tipo = tipo;
		this.costo = costo;
		this.cantidad = cantidad;
	}
	
	//METODOS ABSTRACTOS
	public abstract MateriaPOJO toPOJO();//TO DO
	public abstract Double getPrecioMinimo();
	
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCategoriaPadre() {
		return this.categoriaId;
	}

	public void setCategoria(Integer categoria) {
		this.categoriaId = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.costo = (this.costo / this.cantidad) * cantidad;
		this.cantidad = cantidad;

	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public Boolean isNull() {
		return true;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (nombre.equals("")) {
			errors.put("nombre", "El nombre no puede estar en blanco");
		}
		if (categoriaId == null) {
			errors.put("categoria", "Categoria invalida");
		}
		if (tipo.equals("")) {
			errors.put("tipo", "Tipo de Materia invalida");
		}
		if (costo < 1) {
			errors.put("costo", "El costo debe ser un numero mayor a 0");
		}
	}

	public void setUnidadDeMedida(String unidadDeMedida) {
		
	}

	public String getUnidadDeMedida() {
		return null;
	}



	// Metodos Elaborada

	public void setReceta(List<Materia> receta) {

	}

	public List<Materia> getReceta() {
		return null;
	}

	public String getRecetaTextoPlano() {
		return null;
	}

	public TreeNodesPOJO toTree() {

		TreeNodesPOJO res = new TreeNodesPOJO();

		String botonEliminarHTML = "<button type='button' class='btn btn-outline btn-info float-end' name='deleteMateria-btn'>"
				+ "<a href='deleteMateria.do?id=" + this.getId() + "'><i class='fa-solid fa-trash'></i></a>"
				+ "</button>";
		res.setText(nombre + botonEliminarHTML);
		res.setIcon("fa-solid fa-boxes-stacked");;
		return res;
	}


	
}
