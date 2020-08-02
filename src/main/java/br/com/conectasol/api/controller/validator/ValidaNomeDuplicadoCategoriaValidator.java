package br.com.conectasol.api.controller.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.conectasol.api.dto.CategoriaCreateDTO;
import br.com.conectasol.api.model.entidade.Categoria;
import br.com.conectasol.api.repository.CategoriaRepository;

@Component
public class ValidaNomeDuplicadoCategoriaValidator implements Validator {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		CategoriaCreateDTO dto = (CategoriaCreateDTO) target;
		Optional<Categoria> optCategoria = categoriaRepository.findByNome(dto.getNome());
		if (optCategoria.isPresent()) {
			errors.rejectValue("categoria.nome", null, "Já existe uma categoria com o nome " + dto.getNome());
		}
	}

}
