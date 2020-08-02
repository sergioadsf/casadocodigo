package br.com.conectasol.api.controller.validator;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.conectasol.api.dto.CompraCreateDTO;
import br.com.conectasol.api.model.entidade.Estado;

@Component
public class PaisPossuiEstadoValidador implements Validator {
	
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
		Query query = manager.createQuery("select count(e.id) from "+Estado.class.getName()+" e where e.pais.id = :idPais");
		query.setParameter("idPais", dto.getIdPais());
		Long quantidade = (Long) query.getSingleResult();
		if(quantidade > 0 && Objects.isNull(dto.getIdEstado())) {
			errors.rejectValue("idEstado", null, "Pais possui estados cadastrados, favor selecionar um Estado.");
		}
		
	}

}
