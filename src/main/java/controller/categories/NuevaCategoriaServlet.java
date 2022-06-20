package controller.categories;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.stockmanager.Categoria;
import services.CategoriaService;
import services.StockService;

@WebServlet("/newCategory.do")
public class NuevaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private StockService stockService;
	private CategoriaService categoriaService;

	@Override
	public void init() throws ServletException {
		super.init();
		stockService = new StockService();
		categoriaService = new CategoriaService();
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String catName = req.getParameter("newcategory-name");
		Integer fatherCategory = Integer.parseInt(req.getParameter("category-select"));
		
		Categoria tmp_cat = categoriaService.create(catName, fatherCategory);

		if (tmp_cat.isValid()) {
			resp.sendRedirect("stock.do");
		} else {
			resp.sendRedirect("stock.do");   

	}
}
}