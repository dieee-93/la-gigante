package model.system.stockmanager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.system.materiaprima.Materia;

public class Categoria {
	private Integer id;
	private String nombre;
	private Integer idCategoriaPadre;
	private List<Materia> materia_prima = new LinkedList<Materia>();
	private List<Categoria> subCategoria = new LinkedList<Categoria>();
	private HashMap<String, String> errors = new HashMap<String, String>();
	

	public Categoria(Integer id, String nombre, Integer idCategoriaPadre) {
		this.id = id;
		this.nombre = nombre;
		this.idCategoriaPadre = idCategoriaPadre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCategoriaPadre() {
		return idCategoriaPadre;
	}

	public void setIdCategoriaPadre(Integer idCategoriaPadre)  {
		this.idCategoriaPadre = idCategoriaPadre;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Materia> getMateria_prima() {
		return materia_prima;
	}

	public void setMateria_prima(List<Materia> materia_prima) {
		this.materia_prima = materia_prima;
	}

	public List<Categoria> getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(List<Categoria> subCategoria) {
		this.subCategoria = subCategoria;
	}
	
	public List<Categoria> getCategoriasHijas() {
		List<Categoria> res = new LinkedList<Categoria>();
		res.add(this);
				if(this.subCategoria.size() > 0) {
					for(Categoria cat : this.subCategoria) {
						res.addAll(cat.getCategoriasHijas());
					}
					
				}
		
		
		return res;
	}

	public Materia getMateriaById(Integer id) {
		Materia m = null;
		for (Materia mat : this.materia_prima) {
			if (mat.getId() == id) {
				m = mat;
			}
		}
			
				for(Categoria cat : this.subCategoria) {
					if(m == null) {
					m = cat.getMateriaById(id);
				}
			}

		return m;
	}



	public Categoria getCategoriaByName(String nombre) {
		Categoria c = null;
		
		if (this.nombre.equals(nombre)) {
			c = this;
		} else {
				for(Categoria cat : this.subCategoria) {
					if (c == null){
					c = cat.getCategoriaByName(nombre);}
				}
		}
		return c;

	}
	
	public Categoria getCategoriaById(Integer id) {
		Categoria c = null;
		
		if (this.id.equals(id)) {
			c = this;
		} else {
				for(Categoria cat : this.subCategoria) {
					if (c == null){
					c = cat.getCategoriaById(id);}
				}
		}
		return c;

	}
	
	
	
	public Boolean categoriaExiste(String nombre) {
		Categoria c = getCategoriaByName(nombre);
		
		return (c != null);
	}
	
	
	public Boolean categoriaExisteById(Integer id) {
		Categoria c = getCategoriaById(id);
		
		return (c != null);
	}
		
	public String getCategoriaHTML(Integer index) {
		String res = "";
		index++;
		res += " " + index + "-" + this.nombre + "/" ;
		for (Categoria cat :this.subCategoria){
			
			
			res += cat.getCategoriaHTML(index);
			
			
			
		}
		
		return res;
	}


	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (nombre.equals("")) {
			errors.put("nombre", "El nombre de la categoria no es valido");
		}
		
				
	}

	public Boolean isNull() {
	
		return false;
	}
	
}
