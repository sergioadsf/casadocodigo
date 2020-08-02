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

import br.com.conectasol.api.dto.EstadoCreateDTO;
import br.com.conectasol.api.model.entidade.Estado;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<Void> novo(@RequestBody @Valid EstadoCreateDTO estadoDTO) {

		Estado estado = estadoDTO.toModel(manager);
		manager.persist(estado);
		return ResponseEntity.ok().build();
	}
}
