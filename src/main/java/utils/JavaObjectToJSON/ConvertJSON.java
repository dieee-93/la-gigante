package utils.JavaObjectToJSON;

import java.util.List;

import model.system.stockmanager.CategoriasMateria;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public final class ConvertJSON {
	
	private ConvertJSON() {
		
	}

	
	public static List<TreeNodesPOJO> getTree(CategoriasMateria categorias) {
		
		return categorias.toTree();

	}
	

}