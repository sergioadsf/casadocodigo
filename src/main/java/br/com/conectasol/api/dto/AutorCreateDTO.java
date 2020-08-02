package br.com.conectasol.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.conectasol.api.model.entidade.Autor;

public class AutorCreateDTO {

	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;

	@Size(max = 400)
	@NotBlank
	private String descricao;

	public AutorCreateDTO(@NotBlank String nome, @Email @NotBlank String email,
			@Size(max = 400) @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {

		return new Autor(nome, email, descricao);
	}

	@Override
	public String toString() {
		return "CriarAutorDTO [nome=" + nome + ", email=" + email + ", descricao=" + descricao + "]";
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

}
