package services;

import java.util.List;

import model.Usuario;
import model.system.materiaprima.Materia;
import model.system.stockmanager.Stock;
import persistence.commons.DAOFactory;

public class StockService {

	public Stock create(Integer id, Double unidad) {
		
		Materia materia = new Materia(-1, "Diego","RLZ");
		materia.setId(id);
		materia.setUnidades(unidad);
		
		if (materia.isValid()) {
			DAOFactory.getStockDAO().insert(materia);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return Stock.getInstance();
	}

	public List<Materia> list() {
		return DAOFactory.getStockDAO().findAll();
	}

	public Stock update(Integer id, Double cantidad) {
		
		Materia materia = DAOFactory.getStockDAO().find(id);
		
		materia.setId(id);
		materia.setUnidades(cantidad);
		

		if (materia.isValid()) {
			DAOFactory.getStockDAO().update(materia);
		}
		return Stock.getInstance();

	}

	public void delete(Integer id) {
		Materia materia = DAOFactory.getStockDAO().find(id);
		DAOFactory.getStockDAO().delete(materia);
	}

	public Stock getStock() {

		return Stock.getInstance();
	}
	
	public Boolean yaExiste(String username) {
		return username.equals(DAOFactory.getUsuarioDAO().findByUsername(username).getUsername());
	}
	
	public Usuario findyById(Integer id) {
		
		return DAOFactory.getUsuarioDAO().find(id);
	}

}
