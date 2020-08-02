package br.com.conectasol.api.controller.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.conectasol.api.dto.AutorCreateDTO;
import br.com.conectasol.api.model.entidade.Autor;
import br.com.conectasol.api.repository.AutorRepository;

@Component
public class ValidaEmailDuplicadoAutorValidator implements Validator {

	@Autowired
	private AutorRepository autorRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return AutorCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		AutorCreateDTO dto = (AutorCreateDTO) target;
		Optional<Autor> optAutor = autorRepository.findByEmail(dto.getEmail());
		if (optAutor.isPresent()) {
			errors.rejectValue("email", null, "Já existe um(a) autor(a) com o mesmo email " + dto.getEmail());
		}
	}

}
