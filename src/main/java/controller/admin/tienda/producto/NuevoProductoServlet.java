package controller.admin.tienda.producto;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.materiaprima.Materia;
import model.system.tienda.Producto;
import services.ProductoService;

@WebServlet("/newProducto.do")
public class NuevoProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private ProductoService productoService;


	@Override
	public void init() throws ServletException {
		super.init();
		productoService = new ProductoService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//TODO FALTA MUCHA LOGICA
		String productoNombre = req.getParameter("newProducto-nombre");
		String productoDescripcion = req.getParameter("newProducto-descripcion");
		Integer productoCategoria = Integer.parseInt(req.getParameter("newProducto-categoria"));
		Double productoCostoDeProduccion = 0.0;
		
		if (req.getParameter("newProducto-costo") != null) {
			productoCostoDeProduccion = Double.valueOf(req.getParameter("newProducto-costo"));
		}
	
		
		Date productoFechaDeCreacion = new Date(System.currentTimeMillis());
		List<Materia> ingredientes = productoService.lectorDeRecetas(req.getParameter("newProducto-receta"));
		
		Producto tmpProducto = productoService.create(productoNombre, productoDescripcion, productoCategoria,productoCostoDeProduccion, productoFechaDeCreacion, ingredientes);
		
		if (tmpProducto.isValid()) {
			req.getSession().setAttribute("success" , "¡El producto fue creado exitosamente!");
		
		} else {
			
			req.getSession().setAttribute("producto", tmpProducto);
		}
		
		resp.sendRedirect("tienda.do");
		
	}

}