package model.system.materiaprima;

import java.util.LinkedList;
import java.util.List;

import utils.JavaObjectToJSON.POJO.MateriaPOJO;

public class MateriaElaborada extends Materia {

	private List<Materia> receta = new LinkedList<Materia>();
	private String unidadDeMedida;

	public MateriaElaborada(Integer id, String nombre, Integer categoria, String tipo, Double costo, Double cantidad,
			List<Materia> receta) {
		super(id, nombre, categoria, tipo, costo, cantidad);
		this.receta = receta;

	}

	public MateriaElaborada(Integer id, String nombre, Integer categoria, String tipo, Double costo, Double cantidad,
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

	@Override
	public List<Materia> getReceta() {
		return receta;
	}

	@Override
	public void setReceta(List<Materia> receta) {
		this.receta = receta;
	}

	@Override
	public MateriaPOJO toPOJO() {
		MateriaPOJO res = new MateriaPOJO();
		res.setId(this.id);
		res.setNombre(this.nombre);
		res.setCategoria(this.categoriaId);
		res.setTipo(this.tipo);
		res.setCosto(this.costo);
		res.setCantidad(this.cantidad);
		
		if(this.unidadDeMedida != null) {
		res.setUnidadDeMedida(this.unidadDeMedida);
		}
		
		res.setPrecioMinimo(this.getPrecioMinimo());
		
		List<MateriaPOJO> tmpList = new LinkedList<>();
		for (Materia mat : this.receta) {
			tmpList.add(mat.toPOJO());
			
		
		}
		res.setIngredientes(tmpList);
		
		return res;
	}

	@Override
	public Double getPrecioMinimo() {
		Double res = 0.0;
		if (this.unidadDeMedida == null) {   //CASO ELABORADA CONTABLE
			
			if (this.cantidad != 1) {
				res = this.costo / this.cantidad;
				
			} else {
				res = this.costo;
			}
		} else  // CASO ELABORADA CONMESURABLE
		{
			// DEVUELVE EL PRECIO EN GRAMOS O EN MILILITROS
			// Primero revisa la unidad de medida almacenada en el atributo
			if (this.unidadDeMedida.equals("gr") || this.unidadDeMedida.equals("ml")) {
				res = (this.costo / this.cantidad);
			} else {
				res = ((this.costo / this.cantidad) / 1000);
			}
		}
		
			return res;
		
	}

}
