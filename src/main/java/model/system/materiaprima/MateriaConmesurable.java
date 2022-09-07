package model.system.materiaprima;

public class MateriaConmesurable extends Materia {

	private String unidadDeMedida;

	public MateriaConmesurable(int id, String nombre, String categoria, String tipo, Double costo, Double cantidad,
			String unidadDeMedida) {

		super(id, nombre, categoria, tipo, costo, cantidad);
		this.unidadDeMedida = unidadDeMedida;

	}

	@Override
	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		String[] tmp_array = cantidad.split("-");
		this.cantidad = Double.parseDouble(tmp_array[0]);
		this.unidadDeMedida = tmp_array[1];
	}

	@Override
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	@Override
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	
	@Override
	public Double getPrecioMinimo () {
		Double res = 0.0;
		// DEVUELVE EL PRECIO EN GRAMOS O EN MILILITROS
		//Primero revisa la unidad de medida almacenada en el atributo
		if(this.unidadDeMedida.equals("gr") || this.unidadDeMedida.equals("ml")) {
			res = (this.costo / this.cantidad);
		} else {
			res = ((this.costo / this.cantidad) / 1000);
		}
		
		return res;
	}
 /*
	public TreeNodesPOJO toTree() {
		
	  TreeNodesPOJO res = new TreeNodesPOJO();
	  
	  String botonEliminarHTML = "<button type='button' class='btn btn-outline btn-info float-end' name='deleteCategory-btn'>" +  "<a href='deleteMateria.do?id=" + this.getId() + "'><i class='fa-solid fa-trash'></i></a>" + "</button>";
	  res.setText(nombre + botonEliminarHTML);
	  res.setIcon("fa fa-folder");
		
		return res;
	}*/

}
