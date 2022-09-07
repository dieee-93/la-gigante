package persistence;

import java.util.List;

import model.system.materiaprima.Materia;
import persistence.commons.GenericDAO;

public interface MateriaDAO extends GenericDAO<Materia> {
	public abstract Materia findByName(String name);
	public abstract List<Materia> lectorDeRecetas(String receta);
}
