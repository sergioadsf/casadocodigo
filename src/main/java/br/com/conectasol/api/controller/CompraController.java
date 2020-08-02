package br.com.conectasol.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectasol.api.controller.validator.CpfCnpjValidador;
import br.com.conectasol.api.controller.validator.EstadoPertencePaisValidador;
import br.com.conectasol.api.controller.validator.PaisPossuiEstadoValidador;
import br.com.conectasol.api.dto.CompraCreateDTO;
import br.com.conectasol.api.model.entidade.Compra;
import br.com.conectasol.api.repository.CompraRepository;
import br.com.conectasol.api.repository.CupomRepository;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	
	/**
	 * Por algum motivo que ainda não descobri o manager.persist lançava a exception abaixo, para não perder mais tempo, criei o CompraRepository 
	 * javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist
	 */
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private CupomRepository cupomRepository; 
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaisPossuiEstadoValidador estadoPaisValidador;

	@Autowired
	private EstadoPertencePaisValidador estadoPertencePaisValidador;

	@Autowired
	private CpfCnpjValidador cpfCnpjValidador;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPaisValidador, cpfCnpjValidador, estadoPertencePaisValidador);
	}

	@PostMapping
	@Transactional
	private ResponseEntity<String> novo(@RequestBody @Valid CompraCreateDTO compraDTO) {
		Compra compra = compraDTO.toModel(manager, cupomRepository);
		/**
		 * Por algum motivo que ainda não descobri o manager.persist lançava a exception abaixo, para não perder mais tempo, criei o CompraRepository 
		 * javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist
		 */
		compraRepository.save(compra);
		return ResponseEntity.status(HttpStatus.CREATED).body(compra.toString());
	}
}
