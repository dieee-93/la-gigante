package model.system.materiaprima;

import java.util.LinkedList;
import java.util.List;

public class MateriaElaborada extends MateriaConmesurable {

	private List<Materia> receta = new LinkedList<Materia>();

	
	public MateriaElaborada(Integer id, String nombre, String categoria, String tipo, Double costo, Double cantidad,
			List<Materia> receta) {
		super(id, nombre, categoria, tipo, costo, cantidad, null);
		this.receta = receta;

	}
	
	public MateriaElaborada(Integer id, String nombre, String categoria, String tipo, Double costo, Double cantidad, String unidadDeMedida, List<Materia> receta) {
	
		super(id, nombre, categoria, tipo, costo, cantidad, unidadDeMedida);
		this.receta = receta;
	}
	

	public Double calculadorDePrecioTotal() {
		Double res = 0.0;
		for (Materia mat : this.receta) {
			res += mat.getCosto();
		}
		return res;
	}
	
	public String getRecetaTextoPlano() {
		String res = "";

		for (Materia mat : this.receta) {
			switch (mat.getTipo()) {
			case ("conmesurable"):
				MateriaConmesurable tmp_mat = (MateriaConmesurable) mat;
				res += tmp_mat.getId() + "-" + tmp_mat.getCantidad() + "-" + tmp_mat.getUnidadDeMedida() + "/";
				break;
			case ("contable"):
				MateriaContable tmp_mat2 = (MateriaContable) mat;
				res += tmp_mat2.getId() + "-" + tmp_mat2.getUnidades() + "/";
			case ("elaborada"):
				MateriaElaborada tmp_mat3 = (MateriaElaborada) mat;
				res += tmp_mat3.getRecetaTextoPlano();
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
