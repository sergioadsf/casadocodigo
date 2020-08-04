package br.com.conectasol.api.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.conectasol.api.model.entidade.Compra;
import br.com.conectasol.api.model.entidade.Pedido;

public class DetalheCompraReadDTO {

	private final String nomeCompleto;
	private final String email;
	private final Long numeroPedido;
	private final List<ItensPedidoReadDTO> itens;
	private boolean existeCupom;
	private BigDecimal valorCupom;

	public DetalheCompraReadDTO(Compra compra) {
		this.nomeCompleto = String.format("%s %s", compra.getNome(), compra.getSobrenome());
		this.email = compra.getEmail();
		Pedido pedido = compra.getPedido();
		itens = pedido.getItens().stream().map(item -> new ItensPedidoReadDTO(item)).collect(Collectors.toList());
		this.numeroPedido = pedido.getId();
		if (Objects.nonNull(compra.getCupom())) {
			this.existeCupom = true;
			this.valorCupom = compra.getCupom().getPercentualDesconto().multiply(pedido.getTotal()).divide(new BigDecimal(100)).setScale(2);
		}
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public List<ItensPedidoReadDTO> getItens() {
		return itens;
	}

	public boolean isExisteCupom() {
		return existeCupom;
	}

	public BigDecimal getValorCupom() {
		return valorCupom;
	}
	
	

}
