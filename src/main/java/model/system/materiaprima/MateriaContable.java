package model.system.materiaprima;

import utils.JavaObjectToJSON.POJO.MateriaPOJO;

public class MateriaContable extends Materia {


	
	public MateriaContable(int id, String nombre, String categoria, String tipo, Double costo, Double cantidad) {

		super(id, nombre, categoria, tipo, costo, cantidad);


	}

	@Override
	public Double getPrecioMinimo() {
		Double precioUnitario = 0.0;
		if (this.cantidad != 1) {
			precioUnitario = this.costo / this.cantidad;
			
		} else {
			precioUnitario = this.costo;
		}
		return precioUnitario;
	}

	@Override
	public MateriaPOJO toPOJO() {
		MateriaPOJO res = new MateriaPOJO();
		res.setId(this.id);
		res.setNombre(this.nombre);
		res.setCategoria(this.categoria);
		res.setTipo(this.tipo);
		res.setCosto(this.costo);
		res.setCantidad(this.cantidad);
		res.setPrecioMinimo(this.getPrecioMinimo());

		return res;
	}
	

}
