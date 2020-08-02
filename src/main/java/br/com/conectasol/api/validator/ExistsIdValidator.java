package br.com.conectasol.api.validator;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

	@PersistenceContext
	private EntityManager manager;

	private Class<?> clazz;
	private String domainAttribute;

	@Override
	public void initialize(ExistsId params) {
		this.clazz = params.domainClass();
		this.domainAttribute = params.fieldName();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if (Objects.isNull(value)) {
			return true;
		}
		
		Query query = manager
				.createQuery("select count(id) from " + clazz.getName() + " where " + domainAttribute + " = :id");
		query.setParameter("id", value);
		Long qtd = (Long) query.getSingleResult();

		return qtd > 0;
	}

}
