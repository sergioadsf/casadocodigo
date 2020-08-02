package br.com.conectasol.api.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.conectasol.api.model.entidade.ItemPedido;
import br.com.conectasol.api.model.entidade.Livro;
import br.com.conectasol.api.validator.ExistsId;

public class ItensPedidoCompraDTO {

	@NotNull
	@ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long idLivro;

	@NotNull
	@Positive
	private Integer quantidade;

	public ItensPedidoCompraDTO(@NotNull Long idLivro, @NotNull @Positive Integer quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "ItensPedidoCompraDTO [idLivro=" + idLivro + ", quantidade=" + quantidade + "]";
	}

	public ItemPedido toModel(EntityManager manager) {
		return new ItemPedido(manager.find(Livro.class, idLivro), quantidade);
	}

}
