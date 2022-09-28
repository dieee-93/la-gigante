package persistence.commons;

import persistence.CategoriaDAO;
import persistence.MateriaDAO;
import persistence.ProductoDAO;
import persistence.StockDAO;
import persistence.UsuarioDAO;
import persistence.impl.CategoriaDAOImpl;
import persistence.impl.MateriaDAOImpl;
import persistence.impl.ProductoDAOImpl;
import persistence.impl.StockDAOImpl;
import persistence.impl.UsuarioDAOImpl;

public class DAOFactory {
	
	  private DAOFactory() {
		    throw new IllegalStateException("Utility class");
		  }

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static StockDAO getStockDAO() {
		return new StockDAOImpl();
	}
	
	public static MateriaDAO getMateriaDAO() {
		return new MateriaDAOImpl();
	}
	public static CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOImpl();
	}
	public static ProductoDAO getProductoDAO() {
		return new ProductoDAOImpl();
	}
}
