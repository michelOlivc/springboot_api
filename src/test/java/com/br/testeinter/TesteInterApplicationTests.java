package com.br.testeinter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	DigitoUnicoServiceTest.class,
	DigitoUnicoCacheTest.class,
	UsuarioServiceTest.class
})
class TesteInterApplicationTests {

	@Test
	void contextLoads() {
	}

}