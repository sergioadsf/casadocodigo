
package br.com.conectasol.api.dto;

import java.math.BigDecimal;

import br.com.conectasol.api.model.entidade.Livro;

public class LivroDetalheReadDTO {

	private Long id;

	private String titulo;

	private String resumo;

	private BigDecimal preco;

	private Integer numeroPaginas;

	private String isbn;

	private String sumario;

	private String nomeCategoria;

	private DetalheLivroReadDTO autor;

	public LivroDetalheReadDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.sumario = livro.getSumario();
		this.nomeCategoria = livro.getCategoria().getNome();
		this.autor = new DetalheLivroReadDTO(livro.getAutor());
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getSumario() {
		return sumario;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public DetalheLivroReadDTO getAutor() {
		return autor;
	}
	
}
