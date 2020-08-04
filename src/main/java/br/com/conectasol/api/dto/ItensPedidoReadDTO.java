package br.com.conectasol.api.dto;

import br.com.conectasol.api.model.entidade.ItemPedido;
import br.com.conectasol.api.model.entidade.Livro;

public class ItensPedidoReadDTO {

	private final String nomeLivro;
	private final String autorLivro;
	private final String isbn;
	private final Integer quantidade;

	public ItensPedidoReadDTO(ItemPedido item) {
		Livro livro = item.getLivro();
		this.nomeLivro = livro.getTitulo();
		this.autorLivro = livro.getAutor().getNome();
		this.isbn = livro.getIsbn();
		this.quantidade = item.getQuantidade();
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public String getAutorLivro() {
		return autorLivro;
	}

	public String getIsbn() {
		return isbn;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
