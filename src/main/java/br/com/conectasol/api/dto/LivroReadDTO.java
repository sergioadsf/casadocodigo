package br.com.conectasol.api.dto;

import br.com.conectasol.api.model.entidade.Livro;

public class LivroReadDTO {

	private Long id;
	private String nome;

	public LivroReadDTO(Livro livro) {
		super();
		this.id = livro.getId();
		this.nome = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
