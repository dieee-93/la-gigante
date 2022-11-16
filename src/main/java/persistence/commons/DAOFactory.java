package persistence.commons;

import persistence.CategoriasProductoDAO;
import persistence.MateriaDAO;
import persistence.ProductoDAO;
import persistence.StockDAO;
import persistence.UsuarioDAO;
import persistence.impl.CategoriasMateriaDAOImpl;
import persistence.impl.CategoriasProductoDAOImpl;
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
	public static CategoriasProductoDAO getCategoriasMateriaDAO() {
		return new CategoriasMateriaDAOImpl();
	}
	public static CategoriasProductoDAO getCategoriasProductoDAO() {
		return new CategoriasProductoDAOImpl();
	}
	public static ProductoDAO getProductoDAO() {
		return new ProductoDAOImpl();
	}
}
