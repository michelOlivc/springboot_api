package com.br.testeinter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.testeinter.model.DigitoUnicoHistorico;

public interface DigitoUnicoHistoricoRepository extends JpaRepository<DigitoUnicoHistorico, Long> {
	
	public List<DigitoUnicoHistorico> findByUsuario_Id(Long id);
	
	public List<DigitoUnicoHistorico> findByUsuario_Email(String email);
	
}
