package model.system.materiaprima;

import java.util.LinkedList;
import java.util.List;

public class MateriaElaborada extends Materia {

	private List<Materia> receta = new LinkedList<Materia>();
	private String unidadDeMedida;

	public MateriaElaborada(Integer id, String nombre, String categoria, String tipo, Double costo, Double cantidad,
			List<Materia> receta) {
		super(id, nombre, categoria, tipo, costo, cantidad);
		this.receta = receta;

	}

	public MateriaElaborada(Integer id, String nombre, String categoria, String tipo, Double costo, Double cantidad,
			String unidadDeMedida, List<Materia> receta) {

		super(id, nombre, categoria, tipo, costo, cantidad);
		this.unidadDeMedida = unidadDeMedida;
		this.receta = receta;
	}

	@Override
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	@Override
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public Double calculadorDePrecioTotal() {
		Double res = 0.0;
		for (Materia mat : this.receta) {
			res += mat.getCosto();
		}
		return res;
	}

	@Override
	public String getRecetaTextoPlano() {
		String res = "";

		for (Materia mat : this.receta) {
			switch (mat.getTipo()) {
			case ("conmesurable"):
				res += mat.getId() + "-" + mat.getCantidad() + "-" + mat.getUnidadDeMedida() + "/";
				break;
			case ("contable"):
				res += mat.getId() + "-" + mat.getCantidad() + "/";
				break;
			case ("elaborada"):

				res += mat.getRecetaTextoPlano();
				break;

			}

		}
		return res;
	}

	@Override
	public List<Materia> getReceta() {
		return receta;
	}

	@Override
	public void setReceta(List<Materia> receta) {
		this.receta = receta;
	}

}
