package controller.admin.materia;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.system.materiaprima.Materia;
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

		String materiaName = req.getParameter("newMateria-nombre");
		Integer materiaCategory = Integer.parseInt(req.getParameter("newMateria-categoria"));
		String materiaTipo = req.getParameter("newMateria-tipo");
		Double materiaCosto = Double.parseDouble(req.getParameter("newMateria-costo"));
	

		Double materiaCantidad = Double.parseDouble(req.getParameter("newMateria-cantidad"));

		String materiaUnidadDeMedida = req.getParameter("newMateria-unidadDeMedida");
		String materiaReceta = req.getParameter("newMateria-receta");

	
		
			
		//REVISA SI LA MATERIA EXISTE, SI NO EXISTE LA CREA, SI EXISTE AGREGA LA CANTIDAD AL STOCK Y ACTUALIZA PRECIO EN LA LISTA DE MATERIAS PRIMAS
		if (Boolean.TRUE.equals(!stockService.yaExiste(materiaName))) {
			Materia tmpMateria = null;
		switch (materiaTipo) {

		case "conmesurable":
			materiaService.create(materiaName, materiaCategory, materiaTipo, materiaCosto, materiaCantidad,
					materiaUnidadDeMedida);
			tmpMateria = materiaService.findyByName(materiaName);
			stockService.create(tmpMateria.getId(), materiaCantidad, materiaCosto);

			break;
		case "contable":

			materiaService.create(materiaName, materiaCategory, materiaTipo, materiaCosto, materiaCantidad);

			tmpMateria = materiaService.findyByName(materiaName);

			stockService.create(tmpMateria.getId(), materiaCantidad, materiaCosto);

			break;
		case "elaborada":
			materiaService.create(materiaName, materiaCategory, materiaTipo, materiaCosto, materiaCantidad,
					materiaService.lectorDeRecetas(materiaReceta));

			tmpMateria = materiaService.findyByName(materiaName);

			stockService.create(tmpMateria.getId(), materiaCantidad, materiaCosto);

			break;
		default:
			// TODO
			break;
		}

	} else {
		Materia tmpMateria = stockService.findByName(materiaName);
	
		switch(tmpMateria.getTipo()) {
		case "conmesurable":
			materiaService.update(tmpMateria.getId(), tmpMateria.getNombre(), tmpMateria.getCategoriaPadre(), tmpMateria.getTipo(), materiaCosto, materiaCantidad, tmpMateria.getUnidadDeMedida());
			stockService.agregarAStock(tmpMateria.getId(), materiaCantidad, materiaCosto);
			break;
		case "contable":
			materiaService.update(tmpMateria.getId(), tmpMateria.getNombre(), tmpMateria.getCategoriaPadre(), tmpMateria.getTipo(), materiaCosto, materiaCantidad);
			stockService.agregarAStock(tmpMateria.getId(), materiaCantidad, materiaCosto);
			break;
		case "elaborada":
			materiaService.update(tmpMateria.getId(), tmpMateria.getNombre(), tmpMateria.getCategoriaPadre(), tmpMateria.getTipo(), materiaCosto, materiaCantidad, materiaReceta);
			stockService.agregarAStock(tmpMateria.getId(), materiaCantidad, materiaCosto);
			break;
		}
		
		
		
		
	}
}
}