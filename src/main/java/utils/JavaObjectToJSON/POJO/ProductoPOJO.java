

package utils.JavaObjectToJSON.POJO;

import java.sql.Date;
import java.util.List;

import model.system.materiaprima.Materia;

//Creating MobilePhone class  
public class ProductoPOJO {
	// Creating properties of the class
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precioDeVenta;
	private Double costoDeProduccion;
	private Date fechaDeCreacion;
	private List<Materia> ingredientes;

	// Setter and Getters

	@Override
	public String toString() {
		return "   ";

	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecioDeVenta() {
		return precioDeVenta;
	}

	public void setPrecioDeVenta(Double precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}

	public Double getCostoDeProduccion() {
		return costoDeProduccion;
	}

	public void setCostoDeProduccion(Double costoDeProduccion) {
		this.costoDeProduccion = costoDeProduccion;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public List<Materia> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Materia> ingredientes) {
		this.ingredientes = ingredientes;
	}

}