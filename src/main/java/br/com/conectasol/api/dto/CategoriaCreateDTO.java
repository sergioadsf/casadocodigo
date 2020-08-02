package br.com.conectasol.api.dto;

import javax.validation.constraints.NotBlank;

import br.com.conectasol.api.model.entidade.Categoria;
import br.com.conectasol.api.validator.UniqueValue;

public class CategoriaCreateDTO {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	public Categoria toModel() {
		return new Categoria(nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
