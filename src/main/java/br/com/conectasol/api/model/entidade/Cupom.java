package br.com.conectasol.api.model.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

@Entity
@Table(name = "cupom")
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String codigo;
	private @NotNull @Positive BigDecimal percentualDesconto;
	private @FutureOrPresent LocalDate validade;

	@Deprecated
	public Cupom() {
	}

	public Cupom(@NotBlank String codigo, @NotNull @Positive BigDecimal percentualDesconto,
			@Future LocalDate validade) {
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
		this.validade = validade;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public LocalDate getValidade() {
		return validade;
	}

	@Override
	public String toString() {
		return "Cupom [codigo=" + codigo + ", percentualDesconto=" + percentualDesconto + ", validade=" + validade
				+ "]";
	}

	public void aindaValido() {
		LocalDate today = LocalDate.now();
		Assert.isTrue(validade.compareTo(today) >= 0,
				"O cupom expirou na data " + validade.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

}
