package br.com.conectasol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectasol.api.model.entidade.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

}
