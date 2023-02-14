package services;

import java.util.List;

import model.system.Categoria;
import model.system.materiaprima.CategoriasMateria;
import model.system.tienda.CategoriasProducto;
import persistence.commons.DAOFactory;

public class CategoriaService {
	
	// CATEGORIAS MATERIA

	public Categoria createCategoriaMateria(String nombre, Integer id_padre) {

		Categoria categoria = new Categoria(-1, nombre, id_padre);

		if (categoria.isValid()) {
			DAOFactory.getCategoriasMateriaDAO().insert(categoria);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return categoria;
	}

	public List<Categoria> listCategoriaMateria() {
		return DAOFactory.getCategoriasMateriaDAO().findAll();
	}

	public Categoria updateCategoriaMateria(Integer id, String name, Integer id_padre) {
		Categoria cat = new Categoria(id, name, id_padre);

		if (cat.isValid()) {
			DAOFactory.getCategoriasMateriaDAO().update(cat);
		}
		return cat;

	}

	public void deleteCategoriaMateria(Integer id) {
		Categoria cat = DAOFactory.getCategoriasMateriaDAO().find(id);
		DAOFactory.getCategoriasMateriaDAO().delete(cat);
	}


	public CategoriasMateria actualizarCategoriasMateria() {
		
		CategoriasMateria.getInstance().actualizarCategorias(DAOFactory.getCategoriasMateriaDAO().findAll(),
				DAOFactory.getStockDAO().findAll());

		return CategoriasMateria.getInstance();

	}
	
	
	public Boolean categoriaMateriaExiste(Integer id) {
		Categoria cat = DAOFactory.getCategoriasMateriaDAO().find(id);

		return (cat != null);
	}
	
	
	
	// CATEGORIAS PRODUCTO
	
	
	public Categoria createCategoriaProducto(String nombre, Integer id_padre) {

		Categoria categoria = new Categoria(-1, nombre, id_padre);

		if (categoria.isValid()) {
			DAOFactory.getCategoriasProductoDAO().insert(categoria);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return categoria;
	}

	public List<Categoria> listCategoriaProducto() {
		return DAOFactory.getCategoriasProductoDAO().findAll();
	}

	public Categoria updateCategoriaProducto(Integer id, String name, Integer id_padre) {
		Categoria cat = new Categoria(id, name, id_padre);

		if (cat.isValid()) {
			DAOFactory.getCategoriasProductoDAO().update(cat);
		}
		return cat;

	}

	public void deleteCategoriaProducto(Integer id) {
		Categoria cat = DAOFactory.getCategoriasProductoDAO().find(id);
		DAOFactory.getCategoriasProductoDAO().delete(cat);
	}

	
	public CategoriasProducto actualizarCategoriasProducto() {
		CategoriasProducto.getInstance().actualizarCategorias(DAOFactory.getCategoriasProductoDAO().findAll(), DAOFactory.getProductoDAO().findAll());
	
		return CategoriasProducto.getInstance();
	}
	
	public Boolean categoriaProductoExiste(Integer id) {
		Categoria cat = DAOFactory.getCategoriasProductoDAO().find(id);

		return (cat != null);
	}




}
