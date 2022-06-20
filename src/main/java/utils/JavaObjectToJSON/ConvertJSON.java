package utils.JavaObjectToJSON;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import model.system.materiaprima.Materia;
import model.system.stockmanager.Categoria;
import model.system.stockmanager.CategoriasMateria;
import services.CategoriaService;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public class ConvertJSON {
	

	
	
	public static List<TreeNodesPOJO> getTree(List<Categoria> categorias , List<Materia> materias) {
		List<TreeNodesPOJO> res = new LinkedList<TreeNodesPOJO>();
		TreeNodesPOJO treeNode = new TreeNodesPOJO();
		
		if(!categorias.isEmpty()) {
			for(Categoria cat : categorias) {
				TreeNodesPOJO tmp_treeNode = new TreeNodesPOJO();
 				tmp_treeNode.setText(cat.getNombre());
				tmp_treeNode.setIcon("fa fa-folder");
				if(!cat.getSubCategoria().isEmpty()) {
					tmp_treeNode.setNodes(getTree(cat.getSubCategoria(),cat.getMateria_prima()));
				}
				
				res.add(tmp_treeNode);
			
					
			
			}
			
		}
		
		if (!materias.isEmpty()) {
			for(Materia mat : materias) {
				treeNode.setText(mat.getNombre());
				treeNode.setIcon("fa-solid fa-boxes-stacked");
				res.add(treeNode);
			}	
			
		}
		

		return res;
		

	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		TreeNodesPOJO treeNode = new TreeNodesPOJO();
		List<TreeNodesPOJO> treeNodes = new LinkedList<TreeNodesPOJO>();
		
		CategoriaService categoriaService = new CategoriaService();
		
		treeNodes = getTree( CategoriasMateria.getInstance().actualizarCategorias(categoriaService.list()).getCategorias(), CategoriasMateria.getInstance().getMateriasPrimas());
		System.out.println("The JSON representation of Object mobilePhone is ");
		System.out.println(new Gson().toJson(treeNodes));
	}
}