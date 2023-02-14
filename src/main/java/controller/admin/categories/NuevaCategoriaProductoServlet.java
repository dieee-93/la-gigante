 package controller.admin.categories;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.Categoria;
import services.CategoriaService;

@WebServlet("/newProdCategory.do")
public class NuevaCategoriaProductoServlet extends HttpServlet {
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
		
		Categoria tmp_cat = categoriaService.createCategoriaProducto(catName, fatherCategory);

		if (tmp_cat.isValid()) {
			//TODO
			resp.sendRedirect("tienda.do");
		} else {
			resp.sendRedirect("tienda.do");   

	}
}
}