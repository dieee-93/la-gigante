package model.system.stockmanager;

import java.util.LinkedList;
import java.util.List;

import model.system.materiaprima.Materia;


public class CategoriasMateria  {

	private String nombre = "Sin categorizar";
	private List<Categoria> categorias = new LinkedList<Categoria>();
	private List<Materia> materiasPrimas = new LinkedList<Materia>();
	private static CategoriasMateria categoriaPrincipal = new CategoriasMateria();

	public static CategoriasMateria getInstance() {
		return categoriaPrincipal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Materia> getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(List<Materia> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}



	public List<Categoria> getAllCategorias() {
		List<Categoria> res = new LinkedList<Categoria>();
		if (this.categorias.size() > 0) {
			for (Categoria cat : this.categorias) {
				res.addAll(cat.getCategoriasHijas());

			}
		}
		return res;

	}

	public Boolean categoriaExiste(String nombre) {
		Boolean res = false;

		for (Categoria cat : this.categorias) {
			res = cat.categoriaExiste(nombre);
		}
		return res;

	}

	public Materia getMateriaById(Integer id) {
		Materia m = null;
		for (Materia mat : this.materiasPrimas) {
			if (mat.getId() == id) {
				m = mat;
			}
			;
		}

		for (Categoria cat : this.categorias) {
			if (m == null) {
				m = cat.getMateriaById(id);
			}
		}

		return m;
	}

	public String getCategoriaHTML() {
		String res = "";
		for (Categoria cat : this.categorias) {
			res += "<li>" + cat.getNombre() + "<li>";
		}

		return res;
	}

	public Categoria getCategoriaByName(String nombre) {
		Categoria c = null;
		for (Categoria cat : this.categorias) {
			if (c == null) {
				c = cat.getCategoriaByName(nombre);
			}
		}

		return c;
	}

	public Categoria getCategoriaById(Integer id) {
		Categoria c = null;
		for (Categoria cat : this.categorias) {
			if (c == null) {
				c = cat.getCategoriaById(id);
			}
		}

		return c;
	}

}