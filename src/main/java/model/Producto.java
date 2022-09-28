package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.system.materiaprima.Materia;
import utils.JavaObjectToJSON.POJO.ProductoPOJO;

public class Producto {

	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precioDeVenta;
	private Double costoDeProduccion;
	private Date fechaDeCreacion;
	private List<Materia> ingredientes;
	private HashMap<String, String> errors;

	public Producto(Integer id, String nombre, String descripcion, Double costoDeProduccion, Date fechaDeCreacion,
			List<Materia> ingredientes) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoDeProduccion = costoDeProduccion;
		this.fechaDeCreacion = fechaDeCreacion;
		this.ingredientes = ingredientes;
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

	public Boolean isNull() {
		return false;
	}

	public String getIngredientesTextoPlano() {
		String res = "";

		for (Materia mat : this.ingredientes) {
			switch (mat.getTipo()) {
			case ("conmesurable"):
				res += mat.getId() + "-" + mat.getCantidad() + "-" + mat.getUnidadDeMedida() + "/";
				break;
			case ("contable"):
				res += mat.getId() + "-" + mat.getCantidad() + "/";
				break;
			case ("elaborada"):

				if (mat.getUnidadDeMedida() == null) {
					res += mat.getId() + "-" + mat.getCantidad() + "/";
				} else {
					res += mat.getId() + "-" + mat.getCantidad() + "-" + mat.getUnidadDeMedida() + "/";
				}
				break;

			}

		}
		return res;
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (nombre.equals("")) {
			errors.put("nombre", "El nombre ingresado es invalido");
		}

		if (descripcion.equals("")) {
			errors.put("descripcion", "La descripcion no pede estar vacia");
		}
		if (costoDeProduccion < 1) {
			errors.put("costo", "La direccion ingresada es invalida.");
		}
		if (ingredientes.isEmpty()) {
			errors.put("ingredientes", "La lista de ingredientes no puede estar vacia");
		}

	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public ProductoPOJO toPOJO() {
		ProductoPOJO res = new ProductoPOJO();
		res.setId(this.id);
		res.setNombre(this.nombre);
		res.setDescripcion(this.descripcion);
		res.setPrecioDeVenta(this.precioDeVenta);
		res.setCostoDeProduccion(this.costoDeProduccion);
		res.setIngredientes(this.ingredientes);
		
		return res;
	}

}
