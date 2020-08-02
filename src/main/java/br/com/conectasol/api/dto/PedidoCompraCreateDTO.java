package br.com.conectasol.api.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.conectasol.api.model.entidade.Compra;
import br.com.conectasol.api.model.entidade.ItemPedido;
import br.com.conectasol.api.model.entidade.Pedido;

public class PedidoCompraCreateDTO {

	@NotNull
	@Positive
	private BigDecimal total;

	@Size(min = 1)
	private Set<ItensPedidoCompraDTO> itens;

	public PedidoCompraCreateDTO(@NotNull @Positive BigDecimal total, @NotEmpty Set<ItensPedidoCompraDTO> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "PedidoCompraCreateDTO [total=" + total + ", itens=" + itens + "]";
	}

	public Function<Compra, Pedido> toModel(EntityManager manager) {
		Set<ItemPedido> itens = this.itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());
		return (compra) -> {
			Pedido pedido = new Pedido(compra, total, itens);
			Assert.isTrue(pedido.totalIgual(total), "O Total enviado não corresponde ao total real.");
			return pedido;
		};
	}

}
