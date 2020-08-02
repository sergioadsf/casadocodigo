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

import br.com.conectasol.api.controller.validator.ValidaEmailDuplicadoAutorValidator;
import br.com.conectasol.api.dto.AutorCreateDTO;
import br.com.conectasol.api.dto.AutorReadDTO;
import br.com.conectasol.api.repository.AutorRepository;

@RestController
@RequestMapping("/autores")
// 5
public class AutorController {

	private AutorRepository autorRepository;
	
	@Autowired
	private ValidaEmailDuplicadoAutorValidator validaEmailDuplicadoAutorValidator;

	@Autowired
	public AutorController(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(validaEmailDuplicadoAutorValidator);
	}

	@PostMapping
	public ResponseEntity<AutorReadDTO> salvar(@RequestBody @Valid AutorCreateDTO criarAutorDTO) {
		return ResponseEntity.ok(new AutorReadDTO(this.autorRepository.save(criarAutorDTO.toModel())));
	}

}
