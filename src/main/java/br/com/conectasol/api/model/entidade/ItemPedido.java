package br.com.conectasol.api.model.entidade;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class ItemPedido {

	@ManyToOne
	private Livro livro;

	@NotNull
	@Positive
	private Integer quantidade;

	@NotNull
	@Positive
	private BigDecimal preco;

	@Deprecated
	public ItemPedido() {
	}

	public ItemPedido(Livro livro, @NotNull @Positive Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.preco = livro.getPreco();
	}

	public BigDecimal calcularPrecoTotal() {
		return preco.multiply(new BigDecimal(quantidade));
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPedido [livro=" + livro + ", quantidade=" + quantidade + ", preco=" + preco + "]";
	}

}
