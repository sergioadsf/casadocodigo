package br.com.conectasol.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectasol.api.model.entidade.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Optional<Categoria> findByNome(String nome);

}
