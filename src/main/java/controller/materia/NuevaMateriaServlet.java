package controller.materia;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.stockmanager.Materia;
import services.CategoriaService;
import services.MateriaService;
import services.StockService;

@WebServlet("/newMateria.do")
public class NuevaMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private StockService stockService;
	private CategoriaService categoriaService;
	private MateriaService materiaService;

	@Override
	public void init() throws ServletException {
		super.init();
		stockService = new StockService();
		categoriaService = new CategoriaService();
		materiaService = new MateriaService();
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String materiaName = req.getParameter("newMateria-name");
		
		Double materiaCosto = Double.parseDouble(req.getParameter("newMateria-costo"));
		Double materiaCantidad = Double.parseDouble(req.getParameter("newMateria-cantidad"));
		String materiaCategory = req.getParameter("newMateria-category-select");
		Double materiaUnidades = Double.parseDouble(req.getParameter("newMateria-unidades"));
		String unidadDeMedida = req.getParameter("newMateria-unidadDeMedida-select");
		
		
		Materia tmp_materia = materiaService.create(materiaName, materiaCategory, materia);
		stockService.create(materiaService.findyByName(materiaName).getId(), modelo.getUnidades());

		if (tmp_materia.isValid()) {
			resp.sendRedirect("stock.do");
		} else {
			resp.sendRedirect("stock.do");
		}

	}
}