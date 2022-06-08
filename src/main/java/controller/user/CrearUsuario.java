package controller.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.UsuarioService;

@WebServlet("/create")
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("email");
		String password = req.getParameter("password");
		Long telefono = Long.parseLong(req.getParameter("telefono"));
		String direccion = req.getParameter("direccion");
		
		Usuario tmp_user = usuarioService.create(username, password, telefono, direccion, true);
		

		if (!tmp_user.isValid()) {
			req.setAttribute("flash", "El usuario fue creado con exito.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("login.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("flash", "Se encontraron errores al crear el usuario");
			req.setAttribute("tmp_user", tmp_user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/newuser.jsp");
			dispatcher.forward(req, resp);
		}
	}
}