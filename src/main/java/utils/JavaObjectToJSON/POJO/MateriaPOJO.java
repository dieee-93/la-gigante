package utils.JavaObjectToJSON.POJO;

import java.util.List;

public class MateriaPOJO {

	private Integer id;
	private String nombre;
	private String categoria;
	private String tipo;
	private Double costo;
	private Double cantidad;
	private String unidadDeMedida;
	private Double precioMinimo;
	private List<MateriaPOJO> ingredientes;

	// Setter and Getters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		this.cantidad = cantidad;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public Double getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecioMinimo(Double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public List<MateriaPOJO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<MateriaPOJO> ingredientes) {
		this.ingredientes = ingredientes;

	}

	@Override
	public String toString() {
		return "   ";

	}

}