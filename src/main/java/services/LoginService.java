package services;

import model.Usuario;
import model.nullobjects.NullUser;
import persistence.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public Usuario login(String username, String password) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario user = usuarioDAO.findByUsername(username);

		if (user.isNull() || !user.checkPassword(password)) {
			user = NullUser.build();
		}
		return user;
	}

}
