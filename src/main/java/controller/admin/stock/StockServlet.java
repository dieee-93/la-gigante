package controller.admin.stock;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.materiaprima.Materia;
import model.system.stockmanager.CategoriasMateria;
import services.CategoriaService;
import services.MateriaService;
import services.StockService;
import utils.JavaObjectToJSON.ConvertJSON;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

@WebServlet("/stock.do")
public class StockServlet extends HttpServlet {
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CategoriasMateria mainCategory = categoriaService.actualizarCategoriasMateria();
		List<Materia> listaDeMateriaEnStock = stockService.list();
		List<TreeNodesPOJO> treeNodes = ConvertJSON.getTree(mainCategory);
		String categoriasJSON = new Gson().toJson(treeNodes);

		if (!listaDeMateriaEnStock.isEmpty()) {
			req.setAttribute("mainCategory", mainCategory);
			req.setAttribute("categoriasJSON", categoriasJSON);
			req.setAttribute("listaDeMateria", listaDeMateriaEnStock);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/stock/newStock.jsp");
			dispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO document why this method is empty
	}
}