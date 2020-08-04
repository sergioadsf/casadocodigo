package br.com.conectasol.api.model.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class CupomAplicado {

	@ManyToOne
	private Cupom cupom;

	@NotNull
	@Positive
	private BigDecimal percentualDesconto;

	@FutureOrPresent
	@NotNull
	private LocalDate validade;

	@Deprecated
	public CupomAplicado() {
		super();
	}

	public CupomAplicado(Cupom cupom) {
		this.cupom = cupom;
		this.percentualDesconto = cupom.getPercentualDesconto();
		this.validade = cupom.getValidade();
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

}
