package persistence.commons;

import persistence.CategoriaDAO;
import persistence.MateriaDAO;
import persistence.UsuarioDAO;
import persistence.impl.CategoriaDAOImpl;
import persistence.impl.MateriaDAOImpl;
import persistence.impl.StockDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static MateriaDAO getStockDAO() {
		return new StockDAOImpl();
	}
	
	public static MateriaDAO getMateriaDAO() {
		return new MateriaDAOImpl();
	}
	public static CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOImpl();
	}
}
