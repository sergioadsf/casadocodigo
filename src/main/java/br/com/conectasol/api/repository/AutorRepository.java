package br.com.conectasol.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectasol.api.model.entidade.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

	Optional<Autor> findByEmail(String email);

}
