package com.br.testeinter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.testeinter.model.DigitoUnicoHistorico;
import com.br.testeinter.service.DigitoUnicoHistoricoService;
import com.br.testeinter.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Digito Unico")
@RequestMapping("/digitounico")
public class DigitoUnicoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DigitoUnicoHistoricoService digitoUnicoHistoricoService;
	
	@ApiOperation(value = "Calcula o dígito único. Caso o email seja passado por parâmetro, associa à um usuário.")
	@GetMapping
	public @ResponseBody Integer getDigitoUnico(@RequestParam("numero") String numero,
			@RequestParam("iteracoes") Integer iteracoes, 
			@RequestParam(value = "email", required = false) String email) {
		
		return usuarioService.calcularDigitoUnicoAssociandoAoUsuario(numero, iteracoes, email);
	}
	
	@ApiOperation(value = "Consulta o histórico de cálculos do usuário por id ou email")
	@GetMapping("/all/byUser")
	public @ResponseBody List<DigitoUnicoHistorico> getHistoricosPorUsuario(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "email", required = false) String email) {
		
		if(id != null) {
			return digitoUnicoHistoricoService.findByUsuarioId(id);
		} else if(email != null) {
			return digitoUnicoHistoricoService.findByUsuarioEmail(email);
		}
		
		return null;
	}
	
}
