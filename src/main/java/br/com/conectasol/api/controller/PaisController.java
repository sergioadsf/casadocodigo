package br.com.conectasol.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectasol.api.dto.PaisCreateDTO;

@RestController
@RequestMapping("/paises")
public class PaisController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> salvar(@RequestBody @Valid PaisCreateDTO paisDTO){
		manager.persist(paisDTO.novo());
		return ResponseEntity.ok().build();
	}
}
