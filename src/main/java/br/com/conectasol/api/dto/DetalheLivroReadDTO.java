package br.com.conectasol.api.dto;

import br.com.conectasol.api.model.entidade.Autor;

public class DetalheLivroReadDTO {

	private String nome;
	private String descricao;

	public DetalheLivroReadDTO(Autor autor) {
		this.nome = autor.getNome();
		this.descricao = autor.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
