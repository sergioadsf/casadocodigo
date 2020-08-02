package br.com.conectasol.api.controller.validator;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.conectasol.api.dto.CompraCreateDTO;
import br.com.conectasol.api.model.entidade.Estado;
import br.com.conectasol.api.model.entidade.Pais;

@Component
public class EstadoPertencePaisValidador implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return CompraCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CompraCreateDTO dto = (CompraCreateDTO) target;

		if (errors.hasErrors() || Objects.isNull(dto.getIdEstado())) {
			return;
		}

		Pais pais = manager.find(Pais.class, dto.getIdPais());
		Estado estado = manager.find(Estado.class, dto.getIdEstado());
		
		if(Objects.isNull(estado)) {
			errors.rejectValue("idEstado", null, "Estado selecionado não existe.");
		} else if (!estado.pertence(pais)) {
			errors.rejectValue("idEstado", null, "Estado selecionado não pertence ao pais.");
		}

	}

}
