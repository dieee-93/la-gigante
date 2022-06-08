package services;

import java.util.List;

import model.Usuario;
import persistence.commons.DAOFactory;

public class UsuarioService {

	public Usuario create(String username, String password, Long telefono, String direccion, Boolean admin) {
		Usuario user = new Usuario(-1, username, password, telefono, direccion,  admin);
		user.setPassword(password);

		
		if (user.isValid()) {
			DAOFactory.getUsuarioDAO().insert(user);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return user;
	}

	public List<Usuario> list() {
		return DAOFactory.getUsuarioDAO().findAll();
	}

	public Usuario update(Integer id, String username, String password, Long telefono, String direccion, Boolean admin) {
		Usuario user = DAOFactory.getUsuarioDAO().find(id);

		user.setUsername(username);
		user.setPassword(password);
		user.setTelefono(telefono);
		user.setDireccion(direccion);
		user.setAdmin(admin);
		

		if (user.isValid()) {
			DAOFactory.getUsuarioDAO().update(user);
		}
		return user;

	}

	public void delete(Integer id) {
		Usuario user = DAOFactory.getUsuarioDAO().find(id);
		DAOFactory.getUsuarioDAO().delete(user);
	}

	public Usuario findByName(String username) {

		return DAOFactory.getUsuarioDAO().findByUsername(username);
	}
	
	public Boolean yaExiste(String username) {
		return username.equals(DAOFactory.getUsuarioDAO().findByUsername(username).getUsername());
	}
	
	public Usuario findyById(Integer id) {
		
		return DAOFactory.getUsuarioDAO().find(id);
	}

}

