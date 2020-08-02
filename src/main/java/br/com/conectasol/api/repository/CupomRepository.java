package br.com.conectasol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectasol.api.model.entidade.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

	public Cupom findByCodigo(String codigo);

}
