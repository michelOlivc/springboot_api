package com.br.testeinter.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DigitoUnicoHistorico {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String numero;
	private Integer iteracoes;
	private Integer resultado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public DigitoUnicoHistorico() {}
	
	public DigitoUnicoHistorico(String numero, Integer iteracoes) {
		this.numero = numero;
		this.iteracoes = iteracoes;
	}
	
	public DigitoUnicoHistorico(String numero, Integer iteracoes, Integer resultado) {
		this.numero = numero;
		this.iteracoes = iteracoes;
		this.resultado = resultado;
	}
	
	public DigitoUnicoHistorico(String numero, Integer iteracoes, Integer resultado, Usuario usuario) {
		this.numero = numero;
		this.iteracoes = iteracoes;
		this.resultado = resultado;
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNumero() {
		return numero;
	}

	public Integer getIteracoes() {
		return iteracoes;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setIteracoes(Integer iteracoes) {
		this.iteracoes = iteracoes;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DigitoUnicoHistorico other = (DigitoUnicoHistorico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (iteracoes == null) {
			if (other.iteracoes != null)
				return false;
		} else if (!iteracoes.equals(other.iteracoes))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (resultado == null) {
			if (other.resultado != null)
				return false;
		} else if (!resultado.equals(other.resultado))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
