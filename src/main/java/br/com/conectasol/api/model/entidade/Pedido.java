package br.com.conectasol.api.model.entidade;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private @NotNull Compra compra;

	@ElementCollection
	private @Size(min = 1) Set<ItemPedido> itens;

	private @NotNull @Positive BigDecimal total;

	@Deprecated
	public Pedido() {
	}

	public Pedido(@NotNull Compra compra, @NotNull @Positive BigDecimal total, @Size(min = 1) Set<ItemPedido> itens) {
		this.compra = compra;
		this.total = total;
		this.itens = new HashSet<>(itens);
	}

	public boolean totalIgual(@NotNull @Positive BigDecimal total) {
		BigDecimal totalPedido = itens.stream().map(ItemPedido::calcularPrecoTotal).reduce(BigDecimal.ZERO,
				(atual, proximo) -> atual.add(proximo));
		return totalPedido.doubleValue() == total.doubleValue();
	}

	public Long getId() {
		return id;
	}

	public Compra getCompra() {
		return compra;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public BigDecimal getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "Pedido [itens=" + itens + "]";
	}

}
