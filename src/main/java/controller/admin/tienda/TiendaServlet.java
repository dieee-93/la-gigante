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
import model.system.materiaprima.Materia;
import model.system.tienda.CategoriasProducto;
import model.system.tienda.Producto;
import services.CategoriaService;
import services.MateriaService;
import services.ProductoService;
import utils.JavaObjectToJSON.ConvertJSON;
import utils.JavaObjectToJSON.POJO.MateriaPOJO;
import utils.JavaObjectToJSON.POJO.ProductoPOJO;
import utils.JavaObjectToJSON.POJO.TreeNodesPOJO;

@WebServlet("/tienda.do")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 8308079314140233763L;

	private ProductoService productoService;
	private MateriaService materiaService;
	private CategoriaService categoriaService;

	@Override
	public void init() throws ServletException {
		super.init();
		productoService = new ProductoService();
		materiaService = new MateriaService();
		categoriaService = new CategoriaService();
	}


	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//ARBOL DE CATEGORIAS PRODUCTO
		CategoriasProducto mainCategory = categoriaService.actualizarCategoriasProducto();
		
		
		// LISTA DE PRODUCTOS Y MATERIAS PRIMAS
		List<Producto> productos = productoService.list();
		List<Materia> listaDeMateria = materiaService.list();
		
		
		
		// TO JSON
		List<TreeNodesPOJO> treeNodes = ConvertJSON.getProductoTree(mainCategory);
		List<ProductoPOJO> productosPOJO = ConvertJSON.getProductos(productos);
		List<MateriaPOJO> materiasPOJO = ConvertJSON.getMateria(listaDeMateria);
		
		String categoriasProducto = new Gson().toJson(treeNodes);
		String productosJSON = new Gson().toJson(productosPOJO);
		String materiasJSON = new Gson().toJson(materiasPOJO);
		
		req.setAttribute("mainCategory", mainCategory);
		req.setAttribute("categoriasProducto", categoriasProducto);
		req.setAttribute("productos", productos);
		req.setAttribute("productosJSON", productosJSON);
		req.setAttribute("listaDeMateria", listaDeMateria);
		req.setAttribute("materiasJSON", materiasJSON);
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/tienda/index.jsp");
		dispatcher.forward(req, resp);
		

}
}