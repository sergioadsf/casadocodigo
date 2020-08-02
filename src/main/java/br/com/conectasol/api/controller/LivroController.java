package br.com.conectasol.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectasol.api.dto.LivroCreateDTO;
import br.com.conectasol.api.dto.LivroDetalheReadDTO;
import br.com.conectasol.api.dto.LivroReadDTO;
import br.com.conectasol.api.model.entidade.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<LivroReadDTO> novo(@RequestBody @Valid LivroCreateDTO dto) {

		Livro livro = dto.toModel(manager);
		manager.persist(livro);
		return ResponseEntity.ok(new LivroReadDTO(livro));
	}

	@GetMapping
	public List<LivroReadDTO> listar() {
		List<Livro> list = manager.createQuery("from " + Livro.class.getName(), Livro.class).getResultList();
		return list.stream().map(LivroReadDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDetalheReadDTO> detalhe(@PathVariable("id") Long id) {
		Livro livro = manager.find(Livro.class, id);
		if (livro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new LivroDetalheReadDTO(livro));
	}

}
