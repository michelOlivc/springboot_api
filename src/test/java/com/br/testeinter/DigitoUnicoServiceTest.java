package com.br.testeinter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.testeinter.cache.DigitoUnicoCache;
import com.br.testeinter.error.exception.TesteInterException;
import com.br.testeinter.service.DigitoUnicoService;
import com.br.testeinter.service.UsuarioService;

@SpringBootTest
public class DigitoUnicoServiceTest {
	
	private DigitoUnicoService digitoUnicoService = DigitoUnicoService.getInstance();
	
	@Autowired
	private UsuarioService usuarioService;
	
	@BeforeEach
	public void limparCacheERegistros() {
		usuarioService.deleteAll();
		DigitoUnicoCache.limparCache();
	}
	
	@Test
	public void quandoNumeroForVazioEsperarException() {
		
		Assertions.assertThrows(NumberFormatException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("", 5);
		});
	}
	
	@Test
	public void quandoNumeroContiverSimbolosEsperarException() {
		
		Assertions.assertThrows(NumberFormatException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("12$3", 2);
		});
	}
	
	@Test
	public void quandoNumeroForNuloEsperarException() {
		
		Assertions.assertThrows(TesteInterException.class, () -> {
			digitoUnicoService.calcularDigitoUnico(null, 5);
		});
	}
	
	@Test
	public void quandoNumeroForZeroEsperarException() {
		
		Assertions.assertThrows(TesteInterException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("-0001", 3);
		});
	}
	
	@Test
	public void quandoNumeroForNegativoEsperarException() {
		
		Assertions.assertThrows(TesteInterException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("-0001", 3);
		});
	}
	
	@Test
	public void quandoNumeroDeIteracoesForZeroEsperarException() {
		
		Assertions.assertThrows(TesteInterException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("123789", 0);
		});
	}

	@Test
	public void quandoNumeroDeIteracoesForNegativoEsperarException() {
		
		Assertions.assertThrows(TesteInterException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("123789", -333);
		});
	}
	
	@Test
	public void quandoNumeroDeIteracoesForMaiorQueOLimiteEsperarException() {
		
		Assertions.assertThrows(TesteInterException.class, () -> {
			digitoUnicoService.calcularDigitoUnico("123789", 100001);
		});
	}
	
	@Test
	public void testCalcularDigitoUnico() {
		
		assertEquals(digitoUnicoService.calcularDigitoUnico("45623", 3), 6);
		assertNotEquals(digitoUnicoService.calcularDigitoUnico("112", 2), 6);
	}
	
	@Test
	public void testReplicarNumero() {
		
		assertEquals(digitoUnicoService.somarDigitos("456232211"), 26);
		assertEquals(digitoUnicoService.somarDigitos("112"), 4);
		assertNotEquals(digitoUnicoService.somarDigitos("1"), 2);
	}

}
