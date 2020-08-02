package br.com.conectasol.api.model.entidade;

import java.math.BigDecimal;
import java.util.function.Function;

import javax.persistence.CascadeType;
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

	private BigDecimal valorFinal;

	@ManyToOne(optional = false)
	private @NotNull Pais pais;

	private @NotBlank String telefone;

	private @NotBlank String cep;

	@ManyToOne
	private Estado estado;

	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Pedido pedido;

	@ManyToOne
	private Cupom cupom;

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
		this.valorFinal = pedido.getTotal();
	}

	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.isTrue(estado.pertence(pais), "Este pais não pertence ao pais associado.");
		this.estado = estado;
	}

	public void aplicar(Cupom cupom) {
		this.cupom = cupom;
		this.valorFinal = valorFinal.subtract(cupom.getPercentualDesconto().multiply(BigDecimal.valueOf(0.1)));
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpfCnpj="
				+ cpfCnpj + ", endereco=" + endereco + ", complemento=" + complemento + ", valorFinal=" + valorFinal
				+ ", pais=" + pais + ", telefone=" + telefone + ", cep=" + cep + ", estado=" + estado + ", pedido="
				+ pedido + ", cupom=" + cupom + "]";
	}

}
