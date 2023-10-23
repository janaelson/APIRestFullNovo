package org.serratec.projetofinal.ApiRestful.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.serratec.projetofinal.ApiRestful.DTO.ComentarioDTO;
import org.serratec.projetofinal.ApiRestful.DTO.PostagemDTO;
import org.serratec.projetofinal.ApiRestful.model.Comentario;
import org.serratec.projetofinal.ApiRestful.model.Postagem;
import org.serratec.projetofinal.ApiRestful.repository.ComentarioRepository;
import org.serratec.projetofinal.ApiRestful.repository.PostagemRepository;
import org.serratec.projetofinal.ApiRestful.service.PostagemService;
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
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private PostagemService postagemService;

	@Autowired
	public PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		List<PostagemDTO> postagens = postagemService.listarPostagens();
		return ResponseEntity.ok(postagens);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
		PostagemDTO postagemDTO = postagemService.buscarPostagemPorId(id);
		List<Comentario> comentarios = comentarioRepository.buscarComentarios(id);
		List<ComentarioDTO> comentarioDTO = comentarios.stream()
				.map(postcomentarios -> new ComentarioDTO(postcomentarios)).collect(Collectors.toList());

		if (postagemDTO == null) {
			return ResponseEntity.notFound().build();
		}
		postagemDTO.setComentarios(comentarioDTO);
		return ResponseEntity.ok(postagemDTO);
	}

	@PostMapping
	public ResponseEntity<PostagemDTO> inserirPostagem(@Valid @RequestBody Postagem postagem) {
		PostagemDTO postagemDTO = postagemService.inserirPostagem(postagem);
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		PostagemDTO postagemDTO = postagemService.buscarPostagemPorId(id);
		if (postagemDTO != null) {
			postagem.setId(id);
			postagemRepository.save(postagem);

			return ResponseEntity.ok(postagemDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		postagemService.excluirPostagem(id);
		return ResponseEntity.noContent().build();
	}
}