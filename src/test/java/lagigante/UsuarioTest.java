package lagigante;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import services.LoginService;
import services.UsuarioService;

public class UsuarioTest {


	UsuarioService usuarioService;
	LoginService loginService;

	@Before
	public void setUp() throws Exception {

		usuarioService = new UsuarioService();
		loginService = new LoginService();
	}

	@Test
	public void test() {
	//USUARIO SERVICE TEST
	assertEquals("Diego", loginService.login("Diego", "1234").getUsername());
	assertEquals("Diego", usuarioService.findByName("Diego").getUsername());
	}

}
