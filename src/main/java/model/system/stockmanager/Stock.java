package model.system.stockmanager;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.system.materiaprima.CategoriasMateria;
import model.system.materiaprima.Materia;

public class Stock {

	private HashMap<Integer, String> materiaEnStock = new HashMap<Integer, String>();

	private HashMap<String, Date> ingresosDeMercaderia = new HashMap<String, Date>();

	static private Stock stock = new Stock();

	private Stock() {

	}

	static public Stock getInstance() {
		return stock;
	}

	public HashMap<Integer, String> getMateriaEnStock() {
		return materiaEnStock;
	}

	
	public List<Materia> getMateriaEnStockList(CategoriasMateria categorias){
		List <Materia> res = new LinkedList<Materia>();
		for (Integer matId : materiaEnStock.keySet()) {
			Materia tmp_mat = categorias.getMateriaById(matId);
			tmp_mat.setCantidad(Double.parseDouble(materiaEnStock.get(matId)));
			res.add(tmp_mat);
		}
		return res;
	}

	public void setMateriaEnStock(Map<Integer, String> productosEnStock) {
		productosEnStock.putAll(productosEnStock);
	}

	public void setMateriaEnStock(Integer materiaId, String cantidad) {
		materiaEnStock.put(materiaId, cantidad);
	}

	public HashMap<String, Date> getIngresosDeMercaderia() {
		return ingresosDeMercaderia;
	}

	public void setIngresosDeMercaderia(Map<String, Date> ingresosDeMercaderia) {
		this.ingresosDeMercaderia = (HashMap<String, Date>) ingresosDeMercaderia;
	}

	public static Stock getStock() {
		return stock;
	}

	public static void setStock(Stock stock) {
		Stock.stock = stock;
	}

	public Materia getMateriaById(Integer id) {
		Materia mat = null;
		if (materiaEnStock.containsKey(id)) {
			mat = CategoriasMateria.getInstance().getMateriaById(id);
			mat.setCantidad(Double.parseDouble(materiaEnStock.get(id)));
		}
		return mat;
	}

	public static void main(String[] args) {
		System.out.println(39 / 20);
	}
}
