package com.br.testeinter;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.testeinter.cache.DigitoUnicoCache;
import com.br.testeinter.model.DigitoUnicoHistorico;
import com.br.testeinter.model.Usuario;
import com.br.testeinter.service.DigitoUnicoHistoricoService;
import com.br.testeinter.service.UsuarioService;

@SpringBootTest
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DigitoUnicoHistoricoService digitoUnicoHistoricoService;
	
	@Test
	public void testConsultaPorEmail() {
		
		Usuario usuario = new Usuario("Tifa Lockhart", "tifa@square.com");
		
		usuarioService.save(usuario);
		
		assertEquals(usuarioService.findByEmail(usuario.getEmail()), usuario);
		assertNull(usuarioService.findByEmail(null));
	}
	
	@Test
	public void testConsultaHistoricosDoUsuario() {
		
		Usuario u = new Usuario("Zack Fair", "zack@square.com");
		usuarioService.save(u);
		
		List<DigitoUnicoHistorico> historicos = new ArrayList<DigitoUnicoHistorico>();
		
		historicos.add(new DigitoUnicoHistorico("1123", 2, 5, u));
		historicos.add(new DigitoUnicoHistorico("3541", 3, 2, u));
		historicos.add(new DigitoUnicoHistorico("885", 1, 3, u));
		
		digitoUnicoHistoricoService.saveAll(historicos);
		
		DigitoUnicoHistorico historico = historicos.get(0);
		
		List<DigitoUnicoHistorico> historicosAposSalvar = digitoUnicoHistoricoService.findByUsuarioEmail(u.getEmail());
		
		assertTrue(historicosAposSalvar.contains(historico));
	}
	
	@Test
	public void testCalcularDigitoUnicoEAssociarAUmUsuario() {
		
		Usuario u1 = new Usuario("Cloud Strife", "cloud@square.com");
		usuarioService.save(u1);
		
		Integer resultado = usuarioService.calcularDigitoUnicoAssociandoAoUsuario("456232211", 2, u1.getEmail());
		
		List<DigitoUnicoHistorico> historicos = digitoUnicoHistoricoService.findByUsuarioEmail(u1.getEmail());
		
		assertEquals(resultado, 7);
		assertTrue(historicos != null && !historicos.isEmpty());
	}
	
	@AfterEach
	public void limparRegistrosDeTeste() {
		digitoUnicoHistoricoService.deleteAll();
		usuarioService.deleteAll();
		DigitoUnicoCache.limparCache();
	}
}
