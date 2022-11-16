package services;

import java.util.List;

import model.system.materiaprima.Materia;
import model.system.materiaprima.MateriaConmesurable;
import model.system.stockmanager.Stock;
import persistence.commons.DAOFactory;

public class StockService {

	public Stock create(int id, Double cantidad, Double costo) {
		
		Materia materia = new MateriaConmesurable(id, "1", 1, "1111", costo, cantidad, "Kg");;
	
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
		
		materia.setCantidad(cantidad);
		

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
	
	public Boolean yaExiste(int id) {
		return id == DAOFactory.getStockDAO().find(id).getId();
	}
	
	public Boolean yaExiste(String nombre) {
		return nombre.equals(DAOFactory.getStockDAO().findByName(nombre).getNombre());
	}
	
	
	public Materia findById(Integer id) {
		
		return DAOFactory.getStockDAO().find(id);
	}
	
	public Materia findByName(String nombre) {
		return DAOFactory.getStockDAO().findByName(nombre);
	}
	
	public Materia agregarAStock(Integer id, Double cantidad, Double costo) {
		Materia materia = DAOFactory.getStockDAO().find(id);
		
		materia.setCantidad(cantidad);
		materia.setCosto(costo);
		
		if(materia.isValid()) {
			DAOFactory.getStockDAO().agregarAStock(materia);
		}
		
		return materia;
		
	}

}
