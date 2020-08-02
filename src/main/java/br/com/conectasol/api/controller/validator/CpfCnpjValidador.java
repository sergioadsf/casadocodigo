package br.com.conectasol.api.controller.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.conectasol.api.dto.CompraCreateDTO;

@Component
public class CpfCnpjValidador implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return CompraCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		CompraCreateDTO dto = (CompraCreateDTO) target;
		if (!dto.documentoValido()) {
			errors.rejectValue("cpfCnpj", null, "Favor informar CPF/CNPJ válido!");
		}
	}

}
