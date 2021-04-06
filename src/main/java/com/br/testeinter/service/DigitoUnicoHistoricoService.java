package com.br.testeinter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.testeinter.model.DigitoUnicoHistorico;
import com.br.testeinter.repository.DigitoUnicoHistoricoRepository;

@Service
public class DigitoUnicoHistoricoService {
	
	@Autowired
	private DigitoUnicoHistoricoRepository digitoUnicoHistoricoRepository;
	
	public List<DigitoUnicoHistorico> findByUsuarioId(Long id) {
		return digitoUnicoHistoricoRepository.findByUsuario_Id(id);
	}
	
	public List<DigitoUnicoHistorico> findByUsuarioEmail(String email) {
		return digitoUnicoHistoricoRepository.findByUsuario_Email(email);
	}
	
	public DigitoUnicoHistorico save(DigitoUnicoHistorico historico) {
		return digitoUnicoHistoricoRepository.save(historico);
	}
	
	public List<DigitoUnicoHistorico> saveAll(List<DigitoUnicoHistorico> historicos) {
		return digitoUnicoHistoricoRepository.saveAll(historicos);
	}
	
	public void deleteAll() {
		digitoUnicoHistoricoRepository.deleteAll();
	}
}
