package lagigante;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.system.stockmanager.Categoria;
import model.system.stockmanager.CategoriasMateria;
import model.system.stockmanager.Materia;
import services.CategoriaService;

public class CategoriaTest {

	CategoriaService categoriaService;

	CategoriasMateria categoriaPrincipal;
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
		
		
		categoriaPrincipal = CategoriasMateria.getInstance();
		
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
		
		
		materia1 = new Materia(1,"Provolone","Quesos Semiduros");
		materia2 = new Materia(2,"Peccorino","Quesos Picantes");
		materia3 = new Materia(3,"Bola de lomo", "Carnes");
	
		
		
		categoria4.getMateria_prima().add(materia1);
		categoria7.getMateria_prima().add(materia2);
		
		categorias = new LinkedList<Categoria>();
		categorias2 = categoriaService.list();
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

		
		
		
		categoriaPrincipal.actualizarCategorias(categorias);
		

	}

	@Test
	public void test() {
	//GET CATEGORIA BY NAME ()
	assertEquals("Quesos Cremas", categoriaPrincipal.getCategorias().get(0).getSubCategoria().get(0).getNombre());
	assertEquals("Lacteos", categoriaPrincipal.getCategoriaByName("Lacteos").getNombre());
	assertEquals("Quesos Duros", categoriaPrincipal.getCategoriaByName("Quesos Duros").getNombre());
	assertEquals("Quesos SemiBlandos", categoriaPrincipal.getCategoriaByName("Quesos SemiBlandos").getNombre());
	assertEquals("Carnes", categoriaPrincipal.getCategoriaByName("Carnes").getNombre());
	assertEquals("Quesos", categoriaPrincipal.getCategoriaByName("Quesos").getNombre());
	assertEquals("Vaca", categoriaPrincipal.getCategoriaByName("Vaca").getNombre());
	assertEquals("Quesos Picantes", categoriaPrincipal.getCategoriaByName("Quesos Picantes").getNombre());
	assertEquals("Refrigerados", categoriaPrincipal.getCategoriaById(3).getNombre());
	assertEquals("Provolone", categoriaPrincipal.getCategorias().get(0).getNombre());
	assertEquals("Peccorino", categoria7.getMateria_prima().get(0).getNombre());




	}

}
