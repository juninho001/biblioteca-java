package com.nickollascoelho.biblioteca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "dataemprestimo")
	private Date dataEmprestimo;
	@ManyToOne
	@JoinColumn(name = "idpessoa")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name = "idlivro")
	private Livro livro;
	@Column(name = "datahoradevolucao")
	private Date dataHoraDevolucao;

	public Emprestimo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date getDataHoraDevolucao() {
		return dataHoraDevolucao;
	}

	public void setDataHoraDevolucao(Date dataHoraDevolucao) {
		this.dataHoraDevolucao = dataHoraDevolucao;
	}

}
