package com.br.testeinter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.testeinter.model.Usuario;
import com.br.testeinter.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Usuario")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "Retorna todos os usuários cadastrados.")
	@GetMapping
	public @ResponseBody List<Usuario> all() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Retorna o usuário com o id informado.")
	@GetMapping("/{id}")
	public @ResponseBody Usuario one(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@ApiOperation(value = "Cadastra um usuário novo.")
	@PostMapping
	public @ResponseBody Usuario create(@RequestBody Usuario usuario) {
		return service.save(usuario);
	}

	@ApiOperation(value = "Altera os dados do usuário com o id informado.")
	@PutMapping("/{id}")
	public @ResponseBody Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
		return service.update(usuario, id);
	}

	@ApiOperation(value = "Exclui o usuário com o id informado.")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}

}
