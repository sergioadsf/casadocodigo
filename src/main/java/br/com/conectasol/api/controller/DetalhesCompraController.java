package br.com.conectasol.api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectasol.api.dto.DetalheCompraReadDTO;
import br.com.conectasol.api.model.entidade.Compra;
import br.com.conectasol.api.repository.CompraRepository;

@RestController
@RequestMapping("compras")
public class DetalhesCompraController {

	private CompraRepository compraRepository;

	public DetalhesCompraController(CompraRepository compraRepository) {
		this.compraRepository = compraRepository;
	}
	
	@GetMapping("/detalhe/{id}")
	public ResponseEntity<DetalheCompraReadDTO> detalhe(@PathVariable Long id) {
		Optional<Compra> optCompra = compraRepository.findById(id);
		return ResponseEntity.ok(new DetalheCompraReadDTO(optCompra.get()));
	}

}
