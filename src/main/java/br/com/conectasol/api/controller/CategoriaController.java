package br.com.conectasol.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectasol.api.controller.validator.ValidaNomeDuplicadoCategoriaValidator;
import br.com.conectasol.api.dto.CategoriaCreateDTO;
import br.com.conectasol.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ValidaNomeDuplicadoCategoriaValidator validaNomeDuplicadoCategoriaValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(validaNomeDuplicadoCategoriaValidator);
	}
	
	@PostMapping
	public ResponseEntity<Void> nova(@RequestBody @Valid CategoriaCreateDTO categoriaCreateDTO) {
		categoriaRepository.save(categoriaCreateDTO.toModel());
		return ResponseEntity.ok().build();
	}
}
