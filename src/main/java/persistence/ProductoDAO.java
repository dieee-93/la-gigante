package persistence;

import java.util.List;

import model.system.materiaprima.Materia;
import model.system.tienda.Producto;
import persistence.commons.GenericDAO;

public interface ProductoDAO extends GenericDAO<Producto> {
	public abstract Producto findByName(String name);
	public abstract List<Materia> lectorDeRecetas(String receta);
}
