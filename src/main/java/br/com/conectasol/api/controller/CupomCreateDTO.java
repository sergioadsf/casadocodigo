package br.com.conectasol.api.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.conectasol.api.model.entidade.Cupom;
import br.com.conectasol.api.validator.UniqueValue;

public class CupomCreateDTO {

	@NotBlank
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	private String codigo;

	@NotNull
	@Positive
	private BigDecimal percentualDesconto;

	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	public CupomCreateDTO(@NotBlank String codigo, @NotNull @Positive BigDecimal percentualDesconto) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Cupom toModel() {
		return new Cupom(codigo, percentualDesconto, validade);
	}

	@Override
	public String toString() {
		return "CupomCreateDTO [codigo=" + codigo + ", percentualDesconto=" + percentualDesconto + ", validade="
				+ validade + "]";
	}

}
