package br.com.conectasol.api.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.conectasol.api.model.entidade.Estado;
import br.com.conectasol.api.model.entidade.Pais;
import br.com.conectasol.api.validator.ExistsId;
import br.com.conectasol.api.validator.UniqueValue;

public class EstadoCreateDTO {

	@UniqueValue(domainClass = Estado.class, fieldName = "nome")
	@NotBlank
	private String nome;
	
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	public EstadoCreateDTO(String nome, Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(EntityManager manager) {
		Pais pais = manager.find(Pais.class, this.idPais);
		return new Estado(nome, pais);
	}

}
