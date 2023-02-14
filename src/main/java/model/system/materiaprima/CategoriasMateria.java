package model.system.materiaprima;

import java.util.LinkedList;
import java.util.List;

import model.system.Categoria;
import model.system.interfaces.Categorizable;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public class CategoriasMateria {

	private String nombre = "Sin categorizar";
	private List<Categoria> arbolDeCategoriasList = new LinkedList<>();
	private List<Categoria> categorias = new LinkedList<>();
	private List<Materia> materiasPrimas = new LinkedList<>();
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
		for (Materia cate : this.materiasPrimas) {
			if (cate.getId() == id) {
				m = cate;
			}
			
		}

		for (Categoria cat : this.categorias) {
			if (m == null) {
				m = (Materia) cat.getContenidoById(id);
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

	public void actualizarCategorias(List<Categoria> categorias, List<Materia> materiaPrima) {
		this.categorias.clear();
		this.arbolDeCategoriasList.clear();
		for (int i = categorias.size() - 1; i >= 0; i--) {
			this.arbolDeCategoriasList.add(categorias.get(i));
			if (categorias.get(i).getCategoriaPadre() == 0)

			{
				this.categorias.add(categorias.get(i));
				categorias.remove(i);
			}
		}

		while (categorias.size() != 0) {
			for (int x = categorias.size() - 1; x >= 0; x--) {
				if (this.getCategoriaById(categorias.get(x).getCategoriaPadre()) != null) {
					this.getCategoriaById(categorias.get(x).getCategoriaPadre()).getSubCategoria()
							.add(categorias.get(x));
					categorias.remove(x);
				}

			}

		}
		this.materiasPrimas.clear();
		for (Categorizable cate : materiaPrima) {
			if (cate.getCategoriaPadre() == 0) {
				
				this.materiasPrimas.add((Materia) cate);
			} else {
			this.getCategoriaById(cate.getCategoriaPadre()).getContenido().add(cate);
			}
		}

	}

	public List<TreeNodesPOJO> toTree() {
		List<TreeNodesPOJO> res = new LinkedList<>();
		for (Categorizable mat : this.materiasPrimas) {
			res.add(mat.toTree());
		}
		for (Categoria cat : this.categorias) {
			res.add(cat.toTree());
		}
		return res;
	}

}