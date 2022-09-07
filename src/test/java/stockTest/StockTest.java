package stockTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.system.materiaprima.Materia;
import model.system.materiaprima.MateriaConmesurable;
import model.system.stockmanager.Categoria;
import services.StockService;

public class StockTest {

	public StockService stockService;
	public Categoria categoria1;
	public Materia materia1;
	
	@Before
	public void setUp() throws Exception {

	stockService = new StockService();
	categoria1 =  new Categoria(01, "Quesos", 0);
	materia1 = null;
	categoria1.getMateria_prima().add(materia1);
	
	
	}
	
	@Test
	public void test() {
		
		materia1 = stockService.findByName("Muzarella DaPrimaaa");
		assertEquals("Muzarella DaPrima", materia1.getNombre());
		
	}

}
