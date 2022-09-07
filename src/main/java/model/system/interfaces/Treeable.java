package model.system.interfaces;

import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public interface Treeable {

	public int getId();

	public String getNombre();

	public TreeNodesPOJO toTree();

}
