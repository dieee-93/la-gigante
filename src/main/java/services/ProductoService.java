package services;

import java.util.Date;
import java.util.List;

import model.Producto;
import model.system.materiaprima.Materia;
import persistence.commons.DAOFactory;

public class ProductoService {



	public Producto create(String nombre, String descripcion, Double costoDeProduccion, Date fechaDeCreacion, 
			List<Materia> ingredientes) {

		Producto producto = new Producto(-1, nombre, descripcion, costoDeProduccion, fechaDeCreacion, ingredientes);

		
		
			if(producto.isValid()) {
			DAOFactory.getProductoDAO().insert(producto);
			// XXX: si no devuelve "1", es que hubo más errores
			}

		return producto;
	}

	public Producto update(int id, String nombre, String descripcion, Double costoDeProduccion, List<Materia> ingredientes) {

		Producto producto = DAOFactory.getProductoDAO().find(id);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setCostoDeProduccion(costoDeProduccion);
		producto.setIngredientes(ingredientes);

		if (producto.isValid()) {
			DAOFactory.getProductoDAO().update(producto);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return producto;
	}


	// ------------------------------------------------------- //

	public List<Producto> list() {
		return DAOFactory.getProductoDAO().findAll();
	}

	public List<Materia> lectorDeRecetas(String receta) {
		return DAOFactory.getProductoDAO().lectorDeRecetas(receta);
	}

	public void delete(Integer id) {
		Producto producto = DAOFactory.getProductoDAO().find(id);
		DAOFactory.getProductoDAO().delete(producto);
	}

	public Boolean yaExiste(String username) {
		return username.equals(DAOFactory.getUsuarioDAO().findByUsername(username).getUsername());
	}

	public Producto findyById(Integer id) {

		return DAOFactory.getProductoDAO().find(id);
	}

	public Producto findyByName(String name) {

		return DAOFactory.getProductoDAO().findByName(name);
	}

}
