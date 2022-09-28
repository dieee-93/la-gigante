package persistence;

import java.util.List;

import model.Producto;
import model.system.materiaprima.Materia;
import persistence.commons.GenericDAO;

public interface ProductoDAO extends GenericDAO<Producto> {
	public abstract Producto findByName(String name);
	public abstract List<Materia> lectorDeRecetas(String receta);
}
