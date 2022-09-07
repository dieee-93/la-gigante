package model.system.materiaprima;

public class MateriaContable extends Materia {


	
	public MateriaContable(int id, String nombre, String categoria, String tipo, Double costo, Double cantidad) {

		super(id, nombre, categoria, tipo, costo, cantidad);


	}

	@Override
	public Integer getPrecioUnitario() {
		Integer precioUnitario = 0;
		if (this.cantidad != 1) {
			precioUnitario = (this.costo.intValue() / this.cantidad.intValue());
			
		} else {
			precioUnitario = this.costo.intValue();
		}
		return precioUnitario;
	}
	

}
