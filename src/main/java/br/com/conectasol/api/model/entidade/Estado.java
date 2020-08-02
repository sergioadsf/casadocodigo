package br.com.conectasol.api.model.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_pais", nullable = false)
	private Pais pais;

	public Estado() {
	}
	
	public Estado(@NotBlank String nome, Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Pais getPais() {
		return pais;
	}

	public boolean pertence(Pais pais) {
		return this.pais.equals(pais);
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}
	
	
}
