package br.com.conectasol.api.dto;

import javax.validation.constraints.NotBlank;

import br.com.conectasol.api.model.entidade.Pais;
import br.com.conectasol.api.validator.UniqueValue;

public class PaisCreateDTO {

	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais novo() {
		return new Pais(nome);
	}

}
