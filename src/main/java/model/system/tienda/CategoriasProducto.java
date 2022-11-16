package model.system.tienda;

import java.util.LinkedList;
import java.util.List;

import model.system.interfaces.Categorizable;
import model.system.stockmanager.Categoria;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;
public class CategoriasProducto {

	private String nombre = "Sin categorizar";
	private List<Categoria> categorias = new LinkedList<Categoria>();
	private List<Producto> productos = new LinkedList<>();
	private static CategoriasProducto categoriaPrincipal = new CategoriasProducto();

	public static CategoriasProducto getInstance() {
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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setMateriasPrimas(List<Producto> productos) {
		this.productos = productos;
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

	public Categorizable getMateriaById(Integer id) {
		Categorizable m = null;
		for (Categorizable cate : this.productos) {
			if (cate.getId() == id) {
				m = cate;
			}
			;
		}

		for (Categoria cat : this.categorias) {
			if (m == null) {
				m = cat.getContenidoById(id);
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

	public void actualizarCategorias(List<Categoria> categorias, List<Producto> productos) {
		this.categorias.clear();

		for (int i = categorias.size() - 1; i >= 0; i--) {
			if (categorias.get(i).getCategoriaPadre().equals(0))

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
		this.productos.clear();
		for (Producto prod : productos) {
			if (prod.getCategoriaPadre() == 0) {
				
				this.productos.add(prod);
			} else {
			this.getCategoriaById(prod.getCategoriaPadre()).getContenido().add(prod);
			}
		}

	}

	public List<TreeNodesPOJO> toTree() {
		List<TreeNodesPOJO> res = new LinkedList<>();
		for (Categoria cat : this.categorias) {
			res.add(cat.toTree());
		}
		return res;
	}

}