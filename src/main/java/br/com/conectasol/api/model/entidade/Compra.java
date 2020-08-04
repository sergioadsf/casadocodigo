package br.com.conectasol.api.model.entidade;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @Email @NotBlank String email;

	private @NotBlank String nome;

	private @NotBlank String sobrenome;

	private @NotBlank String cpfCnpj;

	private @NotBlank String endereco;

	private @NotBlank String complemento;

	@ManyToOne(optional = false)
	private @NotNull Pais pais;

	private @NotBlank String telefone;

	private @NotBlank String cep;

	@ManyToOne
	private Estado estado;

	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Pedido pedido;

	@Embedded
	private CupomAplicado cupom;

	@Deprecated
	public Compra() {
	}

	public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String cpfCnpj, @NotBlank String endereco, @NotBlank String complemento, Pais pais,
			@NotBlank String telefone, @NotBlank String cep, Function<Compra, Pedido> fnCriaPedido) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.complemento = complemento;
		this.pais = pais;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = fnCriaPedido.apply(this);
	}

	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.isTrue(estado.pertence(pais), "Este pais não pertence ao pais associado.");
		this.estado = estado;
	}

	public void aplicar(Cupom cupom) {
		cupom.aindaValido();
		this.cupom = new CupomAplicado(cupom);
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public Pais getPais() {
		return pais;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public Estado getEstado() {
		return estado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public CupomAplicado getCupom() {
		return cupom;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpfCnpj="
				+ cpfCnpj + ", endereco=" + endereco + ", complemento=" + complemento + ", pais=" + pais + ", telefone="
				+ telefone + ", cep=" + cep + ", estado=" + estado + ", pedido=" + pedido + ", cupom=" + cupom + "]";
	}

}
