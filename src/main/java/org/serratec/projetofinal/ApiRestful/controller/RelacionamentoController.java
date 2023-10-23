package org.serratec.projetofinal.ApiRestful.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.projetofinal.ApiRestful.model.Relacionamento;
import org.serratec.projetofinal.ApiRestful.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relacionamento")
public class RelacionamentoController {

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	@GetMapping
	public ResponseEntity<List<Relacionamento>> listar() {
		return ResponseEntity.ok(relacionamentoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Relacionamento> buscar(@PathVariable Long id) {
		Optional<Relacionamento> relacionamentoOpt = relacionamentoRepository.findById(id);
		if (relacionamentoOpt.isPresent()) {
			return ResponseEntity.ok(relacionamentoOpt.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Relacionamento> inserir(@Valid @RequestBody Relacionamento relacionamento) {
		relacionamento = relacionamentoRepository.save(relacionamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(relacionamento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Relacionamento> atualizar(@PathVariable Long id,
			@Valid @RequestBody Relacionamento relacionamento) {
		Optional<Relacionamento> relacionamentoOpt = relacionamentoRepository.findById(id);
		if (relacionamentoOpt.isPresent()) {
			relacionamento.getId();
			relacionamentoRepository.save(relacionamento);
			return ResponseEntity.ok(relacionamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Relacionamento> relacionamentoOpt = relacionamentoRepository.findById(id);
		if (relacionamentoOpt.isPresent()) {
			relacionamentoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
