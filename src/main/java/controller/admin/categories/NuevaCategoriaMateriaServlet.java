 package controller.admin.categories;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.stockmanager.Categoria;
import services.CategoriaService;

@WebServlet("/newMatCategory.do")
public class NuevaCategoriaMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;


	private CategoriaService categoriaService;

	@Override
	public void init() throws ServletException {
		super.init();

		categoriaService = new CategoriaService();
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String catName = req.getParameter("newcategory-name");
		Integer fatherCategory = Integer.parseInt(req.getParameter("category-select"));
		
		Categoria tmp_cat = categoriaService.createCategoriaMateria(catName, fatherCategory);

		if (tmp_cat.isValid()) {
			//TODO
			resp.sendRedirect("stock.do");
		} else {
			resp.sendRedirect("stock.do");   

	}
}
}