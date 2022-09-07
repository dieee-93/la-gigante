package model.system.stockmanager;

import java.util.LinkedList;
import java.util.List;

import model.system.materiaprima.Materia;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

public class CategoriasMateria {

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

	public void actualizarCategorias(List<Categoria> categorias, List<Materia> materiaPrima) {
		this.categorias.clear();

		for (int i = categorias.size() - 1; i >= 0; i--) {
			if (categorias.get(i).getIdCategoriaPadre().equals(0))

			{
				this.categorias.add(categorias.get(i));
				categorias.remove(i);
			}
		}

		while (categorias.size() != 0) {
			for (int x = categorias.size() - 1; x >= 0; x--) {
				if (this.getCategoriaById(categorias.get(x).getIdCategoriaPadre()) != null) {
					this.getCategoriaById(categorias.get(x).getIdCategoriaPadre()).getSubCategoria()
							.add(categorias.get(x));
					categorias.remove(x);
				}

			}

		}
		this.materiasPrimas.clear();
		for (Materia mat : materiaPrima) {
			if (mat.getCategoria().equals("0")) {
				
				this.materiasPrimas.add(mat);
			} else {
			this.getCategoriaById(Integer.parseInt(mat.getCategoria())).getMateria_prima().add(mat);
			}
		}

	}

	public List<TreeNodesPOJO> toTree() {
		List<TreeNodesPOJO> res = new LinkedList<>();
		for (Materia mat : this.materiasPrimas) {
			res.add(mat.toTree());
		}
		for (Categoria cat : this.categorias) {
			res.add(cat.toTree());
		}
		return res;
	}

}