package br.com.conectasol.api.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectasol.api.model.entidade.Cupom;
import br.com.conectasol.api.repository.CupomRepository;

@RestController
@RequestMapping("/cupons")
public class CupomController {
	
	@Autowired
	private CupomRepository cupomRepository; 
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> novo(@RequestBody @Valid CupomCreateDTO cupomDTO) {
		Cupom cupom = cupomDTO.toModel();
		cupomRepository.save(cupom);
		return ResponseEntity.ok(cupom.toString());
	}
}
