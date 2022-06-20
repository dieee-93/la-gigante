package services;

import java.util.List;

import model.system.materiaprima.Materia;
import model.system.materiaprima.MateriaConmesurable;
import model.system.materiaprima.MateriaContable;
import model.system.materiaprima.MateriaElaborada;
import persistence.commons.DAOFactory;

public class MateriaService {

	// MATERIA CONMESURABLE

	public Materia create(String nombre, String categoria, String tipo, Double costo, Double cantidad,
			String unidadDeMedida) {

		Materia materia = new MateriaConmesurable(-1, nombre, categoria, tipo, costo, cantidad, unidadDeMedida);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}

	// MATERIA CONTABLE

	public Materia create(String nombre, String categoria, String tipo, Double costo, Double cantidad) {

		Materia materia = new MateriaContable(-1, nombre, categoria, tipo, costo, cantidad);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;

	}

	// ------------------------------------------------------- //

	// MATERIA ELABORADA CONMESURABLE

	public Materia create(String nombre, String categoria, String tipo, Double costo, Double cantidad,
			String unidadDeMedida, List<Materia> receta) {

		Materia materia = new MateriaElaborada(-1, nombre, categoria, tipo, costo, cantidad, unidadDeMedida, receta);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}

	// MATERIA ELABORADA CONTABLE

	public Materia create(String nombre, String categoria, String tipo, Double costo, Double cantidad,
			List<Materia> receta) {

		Materia materia = new MateriaElaborada(-1, nombre, categoria, tipo, costo, cantidad, receta);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}
	
	// ------------------------------------------------------- //

	public List<Materia> list() {
		return DAOFactory.getMateriaDAO().findAll();
	}

	public Materia update(int id, String nombre, String categoria, String tipo, Double costo, Integer unidades) {

		Materia materia = DAOFactory.getMateriaDAO().find(id);
		materia.setUnidades(unidades);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().update(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}

	public Materia update(int id, String nombre, String categoria, String tipo, Double costo, Double cantidad,
			String unidadDeMedida) {

		Materia materia = DAOFactory.getMateriaDAO().find(id);
		materia.setCantidad(cantidad);
		materia.setUnidadDeMedida(unidadDeMedida);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().update(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return materia;
	}

	public Materia update(int id, String nombre, String categoria, String tipo, Double costo,
			List<Materia> ingredientes) {

		Materia materia = DAOFactory.getMateriaDAO().find(id);
		materia.setReceta(ingredientes);

		if (materia.isValid()) {
			DAOFactory.getMateriaDAO().update(materia);
			// XXX: si no devuelve "1", es que hubo más errores
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
