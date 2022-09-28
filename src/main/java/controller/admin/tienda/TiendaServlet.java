 package controller.admin.tienda;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import model.system.materiaprima.Materia;
import services.MateriaService;
import services.ProductoService;
import utils.JavaObjectToJSON.ConvertJSON;
import utils.JavaObjectToJSON.POJO.MateriaPOJO;
import utils.JavaObjectToJSON.POJO.ProductoPOJO;

@WebServlet("/tienda.do")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private ProductoService productoService;
	private MateriaService materiaService;

	@Override
	public void init() throws ServletException {
		super.init();
		productoService = new ProductoService();
		materiaService = new MateriaService();
	}


	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Producto> productos = productoService.list();
		List<Materia> listaDeMateria = materiaService.list();
		List<ProductoPOJO> productosPOJO = ConvertJSON.getProductos(productos);
		List<MateriaPOJO> materiasPOJO = ConvertJSON.getMateria(listaDeMateria);
		String productosJSON = new Gson().toJson(productosPOJO);
		String materiasJSON = new Gson().toJson(materiasPOJO);
		
		req.setAttribute("productos", productos);
		req.setAttribute("productosJSON", productosJSON);
		req.setAttribute("listaDeMateria", listaDeMateria);
		req.setAttribute("materiasJSON", materiasJSON);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/tienda/index.jsp");
		dispatcher.forward(req, resp);
		

}
}