package model.system.stockmanager;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.system.materiaprima.Materia;

public class Stock {

	private List<Materia> productosEnStock = new LinkedList<Materia>();

	private HashMap<String, Date> ingresosDeMercaderia;

	static private Stock stock = new Stock();

	private Stock() {

	}

	static public Stock getInstance() {
		return stock;
	}

	public List<Materia> getProductosEnStock() {
		return productosEnStock;
	}

	public void setProductosEnStock(List<Materia> productosEnStock)  {
		this.productosEnStock = productosEnStock;
	}

	public HashMap<String, Date> getIngresosDeMercaderia() {
		return ingresosDeMercaderia;
	}

	public void setIngresosDeMercaderia(String username, Date ingresosDeMercaderia) {
		this.ingresosDeMercaderia.put(username, ingresosDeMercaderia);
	}
	
	public Materia getMateriaById(Integer id) {
		Materia m = null;
		for(Materia mat : this.productosEnStock) {
			if (mat.getId() == id) {
				m = mat;
			}
		}
		return m;
	}
	
	public void addMateriaById(Integer id, Double cant) {
			this.productosEnStock.add(CategoriasMateria.getInstance().getMateriaById(id));
	}
	
	public void updateMateriaById(Integer id, Double cant) {
		if (!this.materiaEnStock(id)) {
			this.productosEnStock.add(CategoriasMateria.getInstance().getMateriaById(id));
		} else {
			this.getMateriaById(id).setCantidad(cant);
		}
		
	
	}
	
	public Boolean materiaEnStock(Integer id) {
		Materia m = this.getMateriaById(id);
		return (m != null);
	}

}