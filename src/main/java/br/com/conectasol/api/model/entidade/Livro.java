package br.com.conectasol.api.model.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String titulo;

	@NotBlank
	@Size(max = 500)
	@Column(nullable = false, length = 500)
	private String resumo;

	@NotNull
	@Min(20)
	@Column(nullable = false)
	private BigDecimal preco;

	@NotNull
	@Min(100)
	@Column(nullable = false)
	private Integer numeroPaginas;

	@NotBlank
	@Column(nullable = false)
	private String isbn;

	@Future
	@Column(nullable = false)
	private LocalDate dataPublicacao;

	private String sumario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_autor")
	private Autor autor;

	@Deprecated
	public Livro() {
	}

	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotNull @Min(20) BigDecimal preco,
			@NotNull @Min(100) Integer numeroPaginas, @NotBlank String isbn, @Future LocalDate dataPublicacao,
			Categoria categoria, Autor autor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}

	public void setSumario(String sumario) {
		this.sumario = sumario;
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

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public String getSumario() {
		return sumario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Autor getAutor() {
		return autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
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
		Livro other = (Livro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", preco=" + preco + ", numeroPaginas="
				+ numeroPaginas + ", isbn=" + isbn + ", dataPublicacao=" + dataPublicacao + ", sumario=" + sumario
				+ ", categoria=" + categoria + ", autor=" + autor + "]";
	}
	
	

}
