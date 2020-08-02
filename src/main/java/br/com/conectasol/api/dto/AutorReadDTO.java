package br.com.conectasol.api.dto;

import java.time.format.DateTimeFormatter;

import br.com.conectasol.api.model.entidade.Autor;

public class AutorReadDTO {

	private String nome;
	private String email;
	private String descricao;
	private String dataCadastro;

	public AutorReadDTO(Autor autor) {
		this.nome = autor.getNome();
		this.email = autor.getEmail();
		this.descricao = autor.getDescricao();
		this.dataCadastro = autor.getDataCadastro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

}
