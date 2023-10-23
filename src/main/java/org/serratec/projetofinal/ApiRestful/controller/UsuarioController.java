package org.serratec.projetofinal.ApiRestful.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.serratec.projetofinal.ApiRestful.DTO.RelacionamentoDTO;
import org.serratec.projetofinal.ApiRestful.DTO.UsuarioDTO;
import org.serratec.projetofinal.ApiRestful.model.Foto;
import org.serratec.projetofinal.ApiRestful.model.Relacionamento;
import org.serratec.projetofinal.ApiRestful.model.Usuario;
import org.serratec.projetofinal.ApiRestful.repository.RelacionamentoRepository;
import org.serratec.projetofinal.ApiRestful.repository.UsuarioRepository;
import org.serratec.projetofinal.ApiRestful.service.FotoService;
import org.serratec.projetofinal.ApiRestful.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	@Autowired
	private FotoService fotoService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		if (usuarioDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioDTO);
	}

	@GetMapping("/sobremim/{id}")
	public ResponseEntity<UsuarioDTO> buscarComSeguidor(@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		List<Relacionamento> seguidores = relacionamentoRepository.buscarRelacionamentos(id);
		List<RelacionamentoDTO> relacionamentoDTO = seguidores.stream()
				.map(relacionamento -> new RelacionamentoDTO(relacionamento)).collect(Collectors.toList());

		if (usuarioDTO == null) {
			return ResponseEntity.notFound().build();
		}
		usuarioDTO.setRelacionamennto(relacionamentoDTO);
		return ResponseEntity.ok(usuarioDTO);
	}

	@GetMapping("/seguidores/{id}")
	public ResponseEntity<List<RelacionamentoDTO>> buscarRelacionamento(@PathVariable Long id) {
		List<Relacionamento> seguidores = relacionamentoRepository.buscarRelacionamentos(id);
		List<RelacionamentoDTO> relacionamentoDTO = seguidores.stream()
				.map(relacionamento -> new RelacionamentoDTO(relacionamento)).collect(Collectors.toList());
		return ResponseEntity.ok(relacionamentoDTO);
	}

	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = fotoService.buscarPorIdUsuario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, foto.getTipo());
		headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<UsuarioDTO> inserir(@RequestPart MultipartFile file, @RequestPart Usuario usuario)
			throws IOException {
		UsuarioDTO usuarioDTO = usuarioService.inserirComFoto(usuario, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);

	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody Usuario usuario) throws IOException {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO = usuarioService.inserir(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);

		if (usuarioDTO != null) {
			usuario.setId(id);
			usuarioRepository.save(usuario);
			return ResponseEntity.ok(usuarioDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
