package br.com.conectasol.api.dto;

import java.util.Objects;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import br.com.conectasol.api.model.entidade.Compra;
import br.com.conectasol.api.model.entidade.Cupom;
import br.com.conectasol.api.model.entidade.Estado;
import br.com.conectasol.api.model.entidade.Pais;
import br.com.conectasol.api.model.entidade.Pedido;
import br.com.conectasol.api.repository.CupomRepository;
import br.com.conectasol.api.validator.ExistsId;

public class CompraCreateDTO {

	@Email
	@NotBlank
//	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	private String cpfCnpj;

	@NotBlank
	private String endereco;

	@NotBlank
	private String complemento;

	@NotBlank
	private String cidade;

	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	private Long idEstado;

	@NotBlank
	private String cep;

	@NotBlank
	private String telefone;

	private String codigoCupom;

	@NotNull
	private PedidoCompraCreateDTO pedido;

	public CompraCreateDTO(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String cpfCnpj, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade,
			@NotNull Long idPais, @NotBlank String cep, @NotBlank String telefone,
			@NotNull PedidoCompraCreateDTO pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.cep = cep;
		this.telefone = telefone;
		this.pedido = pedido;
	}

	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public boolean documentoValido() {
		String documento = cpfCnpj.replace("\\.", "");

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	}

	public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {
		Pais pais = manager.find(Pais.class, idPais);

		Function<Compra, Pedido> fnCriaPedido = this.pedido.toModel(manager);

		Compra compra = new Compra(email, nome, sobrenome, cpfCnpj, endereco, complemento, pais, telefone, cep,
				fnCriaPedido);
		if (Objects.nonNull(idEstado)) {
			compra.setEstado(manager.find(Estado.class, idEstado));
		}

		if (Objects.nonNull(codigoCupom)) {
			Cupom cupom = cupomRepository.findByCodigo(codigoCupom);
			compra.aplicar(cupom);
		}

		return compra;
	}

	@Override
	public String toString() {
		return "CompraCreateDTO [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpfCnpj="
				+ cpfCnpj + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", idPais=" + idPais + ", idEstado=" + idEstado + ", cep=" + cep + ", telefone=" + telefone
				+ ", pedido=" + pedido + "]";
	}

}
