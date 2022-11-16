package model.system.stockmanager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.nullobjects.NullCategoria;
import model.system.interfaces.Categorizable;
import model.system.interfaces.Treeable;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public class Categoria implements Categorizable, Treeable {
	private int id;
	private String nombre;
	private Integer idCategoriaPadre;
	private List<Categorizable> contenido = new LinkedList<Categorizable>();
	private List<Categoria> subCategorias = new LinkedList<Categoria>();
	private HashMap<String, String> errors = new HashMap<String, String>();

	public Categoria(Integer id, String nombre, Integer idCategoriaPadre) {
		this.id = id;
		this.nombre = nombre;
		this.idCategoriaPadre = idCategoriaPadre;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoriaPadre() {
		return idCategoriaPadre;
	}

	public void setIdCategoriaPadre(Integer idCategoriaPadre) {
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

	public List<Categorizable> getContenido() {
		return this.contenido;
	}

	public void setContenido(List<Categorizable> contenido) {
		this.contenido = contenido;
	}

	public List<Categoria> getSubCategoria() {
		return subCategorias;
	}

	public void setSubCategoria(List<Categoria> subCategoria) {
		this.subCategorias = subCategoria;
	}

	public List<Categoria> getCategoriasHijas() {
		List<Categoria> res = new LinkedList<Categoria>();
		res.add(this);
		if (this.subCategorias.size() > 0) {
			for (Categoria cat : this.subCategorias) {
				res.addAll(cat.getCategoriasHijas());
			}

		}

		return res;
	}

	public Categorizable getContenidoById(Integer id) {
		Categorizable m = NullCategoria.build();
		for (Categorizable cate : this.contenido) {
			if (cate.getId() == id) {
				m = cate;
			}
		}

		for (Categoria cat : this.subCategorias) {
			if (m == null) {
				m = cat.getContenidoById(id);
			}
		}

		return m;
	}

	public Categoria getCategoriaByName(String nombre) {
		Categoria c = NullCategoria.build();

		if (this.nombre.equals(nombre)) {
			c = this;
		} else {
			for (Categoria cat : this.subCategorias) {
				if (c == null) {
					c = cat.getCategoriaByName(nombre);
				}
			}
		}
		return c;

	}

	public Categoria getCategoriaById(Integer id) {
		Categoria c = NullCategoria.build();

		if (this.id == id) {
			c = this;
		} else {
			for (Categoria cat : this.subCategorias) {
				if (c == null) {
					c = cat.getCategoriaById(id);
				}
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
		res += " " + index + "-" + this.nombre + "/";
		for (Categoria cat : this.subCategorias) {

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

	@Override
	public TreeNodesPOJO toTree() {

		TreeNodesPOJO res = new TreeNodesPOJO();

		String botonMostrarCategoriaHTML = "<button type='button' class='btn btn-sm btn-outline-info float-end' onClick='mostrarCategoria(" + this.getId() + ")'><i class='fa-solid fa-magnifying-glass'></i></button>";
		String botonEliminarHTML = "<button type='button' class='btn btn-sm btn-outline-info float-end'>"
				+ "<a href='deleteMateria.do?id=" + this.getId() + "'><i class='fa-solid fa-trash'></i></a>"
				+ "</button>";
		res.setText(nombre + botonMostrarCategoriaHTML);
		res.setIcon("fa fa-folder");
		

		if (!this.subCategorias.isEmpty()) {
			List<TreeNodesPOJO> tmpList = new LinkedList<>();
			for (Categoria cat : this.getSubCategoria()) {

				tmpList.add(cat.toTree());

			}
			
			res.setNodes(tmpList);
		
		}
		
		if(!this.contenido.isEmpty()) {
			List<TreeNodesPOJO> tmpList = new LinkedList<>();
			for(Categorizable cate : this.contenido) {
				tmpList.add(cate.toTree());
			}
	
			res.setNodes(tmpList);
			
		}
		

	
		
		return res;
	}

}
