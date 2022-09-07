package services;

import java.util.List;

import model.system.stockmanager.Categoria;
import model.system.stockmanager.CategoriasMateria;
import persistence.commons.DAOFactory;

public class CategoriaService {

	public Categoria create(String nombre, Integer id_padre) {

		Categoria categoria = new Categoria(-1, nombre, id_padre);

		if (categoria.isValid()) {
			DAOFactory.getCategoriaDAO().insert(categoria);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return categoria;
	}

	public List<Categoria> list() {
		return DAOFactory.getCategoriaDAO().findAll();
	}

	public Categoria update(Integer id, String name, Integer id_padre) {
		Categoria cat = new Categoria(id, name, id_padre);

		if (cat.isValid()) {
			DAOFactory.getCategoriaDAO().update(cat);
		}
		return cat;

	}

	public void delete(Integer id) {
		Categoria cat = DAOFactory.getCategoriaDAO().find(id);
		DAOFactory.getCategoriaDAO().delete(cat);
	}

	public Boolean categoriaExiste(Integer id) {
		Categoria cat = DAOFactory.getCategoriaDAO().find(id);

		return (cat != null);
	}

	public CategoriasMateria actualizarCategorias() {

		CategoriasMateria.getInstance().actualizarCategorias(DAOFactory.getCategoriaDAO().findAll(),
				DAOFactory.getStockDAO().findAll());

		return CategoriasMateria.getInstance();

	}

}
