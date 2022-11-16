package utils.JavaObjectToJSON.POJO;

import java.util.List;

//Creating MobilePhone class  
public class TreeNodesPOJO {  
    //Creating properties of the class  
    private String text;    
    private String icon;    
    private boolean expanded;  
    private List<TreeNodesPOJO> nodes = null;
    //Setter and Getters  
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public List<TreeNodesPOJO> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeNodesPOJO> nodes) {
		this.nodes = nodes;
	}
	
	public void setNodes() {
		this.nodes = null;
	}
	
	

	@Override
	public String toString() {
		  return "   "; 
		
		

	}
	
}  