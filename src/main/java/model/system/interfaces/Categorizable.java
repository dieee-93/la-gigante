package model.system.interfaces;

import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public interface Categorizable {

	public int getId();

	public String getNombre();

	public TreeNodesPOJO toTree();

	public Integer getCategoriaPadre();
	

}
