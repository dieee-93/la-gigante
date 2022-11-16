package controller.admin.categories;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CategoriaService;

@WebServlet("/deleteMatCategory.do")
public class BorrarCategoriaMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private CategoriaService categoriaService;

	@Override
	public void init() throws ServletException {
		super.init();
		categoriaService = new CategoriaService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int catId = Integer.parseInt(req.getParameter("id"));

		if (categoriaService.categoriaMateriaExiste(catId)) {
			categoriaService.deleteCategoriaMateria(catId);
			resp.sendRedirect("stock.do");

		}

		else {

			// TODO
			resp.sendRedirect("stock.do");

		}
	}
}
