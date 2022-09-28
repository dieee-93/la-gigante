package utils.JavaObjectToJSON;

import java.util.LinkedList;
import java.util.List;

import model.Producto;
import model.system.materiaprima.Materia;
import model.system.stockmanager.CategoriasMateria;
import utils.JavaObjectToJSON.POJO.MateriaPOJO;
import utils.JavaObjectToJSON.POJO.ProductoPOJO;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public final class ConvertJSON {
	
	private ConvertJSON() {
		
	}

	
	public static List<TreeNodesPOJO> getTree(CategoriasMateria categorias) {
		
		return categorias.toTree();

	}
	
	public static List<ProductoPOJO> getProductos(List<Producto> productos){
		List<ProductoPOJO> res = new LinkedList<>();
		
		for(Producto prod : productos) {
			res.add(prod.toPOJO());
		}
		return res;
	}
	
	public static List<MateriaPOJO> getMateria(List<Materia> materias){
		List<MateriaPOJO> res = new LinkedList<>();
		
		for (Materia mat : materias) {
			res.add(mat.toPOJO());
		}
		return res;
	}
	

}