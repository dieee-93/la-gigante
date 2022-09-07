package persistence;

import model.system.materiaprima.Materia;
import persistence.commons.GenericDAO;

public interface StockDAO extends GenericDAO<Materia> {
	public abstract Materia findByName(String nombre);
	public abstract Materia agregarAStock(Materia mat);
	
}
