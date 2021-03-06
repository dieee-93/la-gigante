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
		String [] tmp_array = cantidad.split("-");
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

}
