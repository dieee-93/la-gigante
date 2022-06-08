package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.stockmanager.Stock;
import services.StockService;

@WebServlet("/index.do")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private StockService stockService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		stockService = new StockService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Stock stock = stockService.getStock();
		
		if(stock.getProductosEnStock().isEmpty()) {
			req.setAttribute("flash", "Parece que es la primera vez que usas la aplicación, crea un stock para empezar.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/stock.do");
			dispatcher.forward(req, resp);
		}

	}
}