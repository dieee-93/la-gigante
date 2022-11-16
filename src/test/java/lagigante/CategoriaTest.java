package lagigante;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.system.materiaprima.Materia;
import model.system.stockmanager.Categoria;
import model.system.stockmanager.CategoriasMateria;
import model.system.tienda.CategoriasProducto;
import services.CategoriaService;
import services.StockService;

public class CategoriaTest {
	StockService stockService;
	CategoriaService categoriaService;

	CategoriasMateria arbolDeCategoriasMateria;
	CategoriasProducto arbolDeCategoriasProducto;
	Categoria categoria1;
	Categoria categoria2;
	Categoria categoria3;
	Categoria categoria4;
	Categoria categoria5;
	Categoria categoria6;
	Categoria categoria7;
	Categoria categoria8;
	Categoria categoria9;
	Categoria categoria10;
	Categoria categoria11;
	Categoria categoria12;
	
	Materia materia1;
	Materia materia2;
	Materia materia3;
	
	String[] array;
	List<Categoria> categorias;
	List<Categoria> categorias2;

	
	@Before
	public void setUp() throws Exception {
		categoriaService = new CategoriaService();
		stockService = new StockService();
		
		arbolDeCategoriasMateria = categoriaService.actualizarCategoriasMateria();
		arbolDeCategoriasProducto = categoriaService.actualizarCategoriasProducto();
		
		
		categoria8 = new Categoria(1, "Insumos Gastronomicos", 0);
		categoria9 = new Categoria(2, "Insumos De Fiambreria", 1);
		categoria10 = new Categoria(3, "Refrigerados", 1);
		categoria11 = new Categoria(4,"Carnes", 3);
		categoria12 = new Categoria(5, "Vaca", 4);
		categoria7 = new Categoria(6,"Quesos Picantes", 11);
		categoria1 = new Categoria(12,"Lacteos", 3);
		categoria3 = new Categoria(10,"Quesos Duros", 11);
		categoria4 = new Categoria(9,"Quesos Semiduros", 11);
		categoria5 = new Categoria(8,"Quesos SemiBlandos", 11);
		categoria6 = new Categoria(7,"Quesos Cremas", 11);
		categoria2 = new Categoria(11,"Quesos", 12);
		
		

		
		categorias = new LinkedList<Categoria>();
		categorias.add(categoria1);
		categorias.add(categoria2);
		categorias.add(categoria3);
		categorias.add(categoria4);
		categorias.add(categoria5);
		categorias.add(categoria6);
		categorias.add(categoria7);
		categorias.add(categoria8);
		categorias.add(categoria9);
		categorias.add(categoria10);
		categorias.add(categoria11);
		categorias.add(categoria12);



	}

	@Test
	public void test1() {
	//GET CATEGORIA BY NAME ()

	assertEquals("Provolone", arbolDeCategoriasProducto.getCategoriaById(1).getContenido().get(0));





	}
	
	@Test
	public void test2() {
		
	
		assertEquals("Muzarella DaPrima", stockService.findByName("Muzarella DaPrima").getNombre());

		
	}

}
