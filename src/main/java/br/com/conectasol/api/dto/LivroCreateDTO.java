package br.com.conectasol.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.conectasol.api.model.entidade.Autor;
import br.com.conectasol.api.model.entidade.Categoria;
import br.com.conectasol.api.model.entidade.Livro;
import br.com.conectasol.api.validator.ExistsId;
import br.com.conectasol.api.validator.UniqueValue;

public class LivroCreateDTO {

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String resumo;

	private String sumario;

	@NotNull
	@Min(value = 20)
	private BigDecimal preco;

	@NotNull
	@Min(value = 100)
	private Integer numeroPaginas;

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private String isbn;

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	@NotNull
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	private Long idAutor;

	public LivroCreateDTO(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, String isbn,
			@NotNull Long idCategoria, @NotNull Long idAutor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}

	public Livro toModel(EntityManager manager) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		Autor autor = manager.find(Autor.class, idAutor);
		Livro livro = new Livro(titulo, resumo, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
		livro.setSumario(sumario);
		return livro;
	}

	/**
	 * No atual momento o Jackson não consegue converter a String do json para
	 * LocalDate no construtor, por esse motivo foi criado esse setter.
	 * 
	 * @param dataPublicacao
	 */
	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

}
