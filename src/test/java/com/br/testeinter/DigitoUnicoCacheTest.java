package com.br.testeinter;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.testeinter.cache.DigitoUnicoCache;
import com.br.testeinter.model.DigitoUnicoHistorico;
import com.br.testeinter.service.DigitoUnicoService;

@SpringBootTest
public class DigitoUnicoCacheTest {
	
	private DigitoUnicoService digitoUnicoService = DigitoUnicoService.getInstance();
	
	@BeforeEach
	public void guardarCalculosEmCache() {
		
		List<DigitoUnicoHistorico> lista = new ArrayList<DigitoUnicoHistorico>();
		
		lista.add(new DigitoUnicoHistorico("44231", 2));
		lista.add(new DigitoUnicoHistorico("231", 4));
		lista.add(new DigitoUnicoHistorico("1478", 4));
		lista.add(new DigitoUnicoHistorico("45689", 2));
		lista.add(new DigitoUnicoHistorico("1478", 5));
		lista.add(new DigitoUnicoHistorico("2567", 5));
		lista.add(new DigitoUnicoHistorico("178", 3));
		lista.add(new DigitoUnicoHistorico("36475", 1));
		lista.add(new DigitoUnicoHistorico("23", 2));
		lista.add(new DigitoUnicoHistorico("899", 2));
		
		for(DigitoUnicoHistorico d : lista) {
			d.setResultado(digitoUnicoService.calcularDigitoUnico(d.getNumero(), d.getIteracoes()));
		}
	}
	
	@Test
	public void testInserirDigitoUnicoEmCache() {
		
		String numero = "85269";
		Integer iteracoes = 5;
		Integer resultado = digitoUnicoService.calcularDigitoUnico(numero, iteracoes);
		
		DigitoUnicoHistorico d1 = new DigitoUnicoHistorico(numero, iteracoes, resultado);
		DigitoUnicoHistorico d2 = DigitoUnicoCache.buscarHistorico(d1.getNumero(), d1.getIteracoes());
		
		assertEquals(d1, d2);
		
		DigitoUnicoHistorico removidoDuranteInclusao = DigitoUnicoCache.buscarHistorico("44231", 2);
		
		assertNull(removidoDuranteInclusao);
	}
	
	@Test
	public void testBuscarHistoricoEmCache() {
		
		String numero = "85269";
		Integer iteracoes = 5;
		Integer resultado = digitoUnicoService.calcularDigitoUnico(numero, iteracoes);
		
		DigitoUnicoHistorico d1 = new DigitoUnicoHistorico(numero, iteracoes, resultado);  
		DigitoUnicoHistorico d2 = DigitoUnicoCache.buscarHistorico(d1.getNumero(), d1.getIteracoes());
		
		assertEquals(d1, d2);
		assertNull(DigitoUnicoCache.buscarHistorico("22222", 1));
	}
	
	@Test
	public void testResultadoCorretoVindoDoCache() {
		
		String numero = "44231";
		Integer iteracoes = 2;
		
		Integer resultado = digitoUnicoService.calcularDigitoUnico(numero, iteracoes);
		Integer resultadoCache = DigitoUnicoCache.buscarResultado(numero, iteracoes);
		
		assertEquals(resultado, resultadoCache);
	}
	
	@AfterEach
	public void limparCalculosEmCache() {
		DigitoUnicoCache.limparCache();
	}
	
}
