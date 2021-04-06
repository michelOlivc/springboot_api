package com.br.testeinter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.testeinter.error.exception.TesteInterException;
import com.br.testeinter.model.DigitoUnicoHistorico;
import com.br.testeinter.model.Usuario;
import com.br.testeinter.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DigitoUnicoHistoricoService digitoUnicoHistoricoService;
	
	private DigitoUnicoService digitoUnicoService = DigitoUnicoService.getInstance();
	
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario save(Usuario usuario) throws TesteInterException {
		
		if(!existsByEmailNotInId(usuario.getEmail(), usuario.getId())) {
			return usuarioRepository.save(usuario);
		} else {
			throw new TesteInterException("Já existe um usuário cadastrado com o email " + usuario.getEmail());
		}
	}
	
	public Usuario update(Usuario usuario, Long id) {
		
		Usuario u = findById(id);
		
		if(usuario != null) {
			u.setNome(usuario.getNome());
			u.setEmail(usuario.getEmail());
			
			save(u);
		}
		
		return u;
	}
	
	public void deleteById(Long id) throws TesteInterException {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		} else {
			throw new TesteInterException("O Usuario com o id " + id + " não existe.");
		}
	}
	
	public Usuario findByEmail(String email) {
		
		if(email != null && !email.isEmpty()) {
			return usuarioRepository.findByEmail(email);
		}
		
		return null;
	}
	
	public List<DigitoUnicoHistorico> buscarHistoricosDoUsuario(String email) {
		return digitoUnicoHistoricoService.findByUsuarioEmail(email);
	}
	
	public void deleteAll() {
		usuarioRepository.deleteAll();
	}
	
	public Integer calcularDigitoUnicoAssociandoAoUsuario(String numero, int iteracoes, String email) {
		
		Integer resultado = digitoUnicoService.calcularDigitoUnico(numero, iteracoes);
		
		Usuario usuario = findByEmail(email);
		
		if(usuario != null) {
			DigitoUnicoHistorico historico = new DigitoUnicoHistorico(numero, iteracoes, resultado, usuario);
			digitoUnicoHistoricoService.save(historico);
		}
		
		return resultado;
	}
	
	public boolean existsByEmailNotInId(String email, Long id) {
		return usuarioRepository.existsByEmailNotInId(email, id);
	}
	
}
