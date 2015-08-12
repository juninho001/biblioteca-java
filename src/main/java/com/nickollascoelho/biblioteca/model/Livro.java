package com.nickollascoelho.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String escritor;
	private Integer anoedicao;
	private Integer classificacao;

	public Livro() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public Integer getAnoedicao() {
		return anoedicao;
	}

	public void setAnoedicao(Integer anoedicao) {
		this.anoedicao = anoedicao;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

}
