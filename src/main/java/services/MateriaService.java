package services;

import java.util.List;

import model.system.materiaprima.Materia;
import model.system.materiaprima.MateriaConmesurable;
import persistence.commons.DAOFactory;

public class MateriaService {

	public Materia create(String nombre, String categoria, String tipo) {
		Materia materia = null;
	
		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}
	

	public Materia create(String nombre, String categoria, String tipo, Double cantidad, String unidadDeMedida) {
		
		Materia materia = new MateriaConmesurable(-1, nombre, categoria, tipo, cantidad, unidadDeMedida);
	

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}

	public List<Materia> list() {
		return DAOFactory.getMateriaDAO().findAll();
	}

	public Materia update(Integer id, String nombre, Double costoPorKilo, Double cantidad, String unidad_de_medida,
			String categoria) {
		Materia materia = DAOFactory.getMateriaDAO().find(id);
		materia.setNombre(nombre);
		materia.setCostoEnKg(costoPorKilo);
		materia.setCantidad(cantidad);
		materia.setUnidadDeMedida(unidad_de_medida);
		materia.setCategoria(categoria);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().update(materia);
		}
		return materia;

	}

	public void delete(Integer id) {
		Materia materia = DAOFactory.getMateriaDAO().find(id);
		DAOFactory.getMateriaDAO().delete(materia);
	}

	public Boolean yaExiste(String username) {
		return username.equals(DAOFactory.getUsuarioDAO().findByUsername(username).getUsername());
	}

	public Materia findyById(Integer id) {

		return DAOFactory.getMateriaDAO().find(id);
	}

	public Materia findyByName(String name) {

		return DAOFactory.getMateriaDAO().findByName(name);
	}

}
