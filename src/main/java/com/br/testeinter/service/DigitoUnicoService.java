package com.br.testeinter.service;

import java.math.BigInteger;

import com.br.testeinter.cache.DigitoUnicoCache;
import com.br.testeinter.error.exception.TesteInterException;
import com.br.testeinter.model.DigitoUnicoHistorico;

public class DigitoUnicoService {

	private static DigitoUnicoService instance = new DigitoUnicoService();
	
	private DigitoUnicoService() {}
	
	public static DigitoUnicoService getInstance() {
		return instance;
	}
	
	public Integer calcularDigitoUnico(String numero, int iteracoes) throws TesteInterException {
		try {
			if(numero != null && !numero.isEmpty()) {
				BigInteger numeroConvertido = new BigInteger(numero);
				
				if(numeroConvertido.doubleValue() < 1 || numeroConvertido.doubleValue() > (Math.pow(10, 1000000))) {
					throw new TesteInterException("Número a ser convertido inválido.");
				}
				
				if(iteracoes < 1 || iteracoes > (Math.pow(10, 5))) {
					throw new TesteInterException("Número de iterações inválido.");
				}
			} else {
				throw new TesteInterException("Número em branco ou nulo.");
			}

			DigitoUnicoHistorico historico = DigitoUnicoCache.buscarHistorico(numero, iteracoes);
			
			if(historico == null) {
				// o tempo de execução é menor calculando a soma dessa forma, comparando com somar os dígitos após concatenados
				Integer soma = somarDigitos(numero) * iteracoes;
				
				String digitoUnico = soma.toString();
				
				// apesar do caráter recursivo da operação, em números com muitos dígitos a pilha de execução pode aumentar o consumo de memória
				while(digitoUnico.length() != 1) {
					digitoUnico = somarDigitos(digitoUnico).toString();
				}
				
				Integer digitoUnicoInt = Integer.parseInt(digitoUnico);
				DigitoUnicoCache.incluir(new DigitoUnicoHistorico(numero, iteracoes, digitoUnicoInt));
				
				return digitoUnicoInt;
			} else {
				return historico.getResultado();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new TesteInterException("O número a ser convertido contém caracteres inválidos.");
		}
	}
	
	public Integer somarDigitos(String numero) {
		
		Integer resultado = 0;
		
		for(int i = 0; i < numero.length(); i++) {
			String digito = String.valueOf(numero.charAt(i));
			resultado += Integer.parseInt(digito);
		}
		
		return resultado;
	}
	
}
