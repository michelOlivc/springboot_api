package com.br.testeinter.cache;

import java.util.ArrayList;
import java.util.List;

import com.br.testeinter.model.DigitoUnicoHistorico;

public class DigitoUnicoCache {
	
	private static List<DigitoUnicoHistorico> cacheList = new ArrayList<DigitoUnicoHistorico>();
	
	public static void incluir(DigitoUnicoHistorico historico) {
		
		cacheList.add(historico);
		
		if(cacheList.size() > 10) {
			cacheList.remove(0);
		}
	}
	
	public static Integer buscarResultado(String numero, int iteracoes) {
		
		DigitoUnicoHistorico historico = buscarHistorico(numero, iteracoes);
		
		if(historico != null)
			return historico.getResultado();
		
		return null;
	}
	
	public static DigitoUnicoHistorico buscarHistorico(String numero, int iteracoes) {
		
		DigitoUnicoHistorico historico = null;
		
		if(numero != null && !numero.isEmpty() && iteracoes > 0) {
			historico = cacheList.stream()
					.filter(d -> d.getNumero().equals(numero) && d.getIteracoes() == iteracoes)
					.findFirst()
					.orElseGet(() -> null);
		}
		
		return historico;	
	}
	
	public static void limparCache() {
		cacheList = new ArrayList<DigitoUnicoHistorico>();
	}
	
}
