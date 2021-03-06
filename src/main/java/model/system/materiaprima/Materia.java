package model.system.materiaprima;

import java.util.HashMap;
import java.util.List;

public abstract class Materia {


	protected int id;
	protected String nombre;
	protected String categoria;
	protected String tipo;
	protected Double costo;
	protected Double cantidad;
	
	protected HashMap<String, String> errors;
	
	public Materia(Integer id, String nombre, String categoria, String tipo, Double costo, Double cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.tipo = tipo;
		this.costo = costo;
		this.cantidad = cantidad;
	}


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


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
		if (categoria == null) {
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
	
	// Contable Methods
	
	public void setUnidades(Integer unidades) {
		
	}
	
	public Integer getUnidades(){
		return null;
	}

	// Elaborada Methods
	
	public void setReceta(List<Materia> receta) {
		
	}
	
	public List<Materia> getReceta() {
		return null;
	}
	
	public String getRecetaTextoPlano() {
		return null;
	}

	
}
