package stockTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.CategoriasMateria;
import model.system.stockmanager.Materia;

public class MateriaTest {

	public Materia materiaPrima1;
	
	@Before
	public void setUp() throws Exception {

		materiaPrima1 = new Materia("Harina Central Norte", new CategoriasMateria(05, "Harinas") );
		materiaPrima1.setCantidad(25000.0);
		materiaPrima1.setUnidadDeMedida("gr");
		materiaPrima1.setCosto(3100.0);
		
		
	}
	
	@Test
	public void test() {
		assertEquals(155.0, materiaPrima1.getCostoEnKg(), 0.1);
	}

}
