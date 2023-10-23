package org.serratec.projetofinal.ApiRestful.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.projetofinal.ApiRestful.DTO.UsuarioDTO;
import org.serratec.projetofinal.ApiRestful.exeption.EmailException;
import org.serratec.projetofinal.ApiRestful.model.Usuario;
import org.serratec.projetofinal.ApiRestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private FotoService fotoService;
	
	public List<UsuarioDTO> listar() {
		List<Usuario> usuarioList = usuarioRepository.findAll();
		
		List<UsuarioDTO> usuarioDtoList = usuarioList.stream().map(u -> {
			return adicionarImagemURI(u);
		}).collect(Collectors.toList());
		
		return usuarioDtoList;
	}

	public UsuarioDTO adicionarImagemURI(Usuario usuario) {
	URI uri = ServletUriComponentsBuilder.
			fromCurrentContextPath()
			.path("/usuario/{id}/foto")
			.buildAndExpand(usuario.getId())
			.toUri();
	
	UsuarioDTO dto = new UsuarioDTO();
	dto.setNome(usuario.getNome());
	dto.setSobrenome(usuario.getSobrenome());
	dto.setDataNascimento(usuario.getDataNascimento());
	dto.setEmail(usuario.getEmail());
	dto.setUrlImagem(uri.toString());
	return dto;
}
	

	
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		/*
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for (Usuario usuario: usuarios) {
			usuariosDTO.add(new UsuarioDTO(usuario));
		}
		*/
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return usuariosDTO;
	}
	
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOpt.get());
		return usuarioDTO;
	}
	
	public UsuarioDTO inserir(Usuario usuario) throws EmailException {
		/*UsuarioDTO usuariosDTO = new UsuarioDTO();*/
		Usuario usuarioEmailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioEmailExistente != null) {
			throw new EmailException("Email já cadastrado.");
		}

		usuario = usuarioRepository.save(usuario);
		return adicionarImagemURI(usuario);
	}


	public UsuarioDTO inserirComFoto(Usuario usuario, MultipartFile file) throws EmailException, IOException {
		/*UsuarioDTO usuariosDTO = new UsuarioDTO();*/
		Usuario usuarioEmailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioEmailExistente != null) {
			throw new EmailException("Email já cadastrado.");
		}
	
		fotoService.inserir(usuario, file);
		usuarioRepository.save(usuario);
		return adicionarImagemURI(usuario);
	}
	
	public Usuario atualizarComFoto(Usuario usuario, MultipartFile file) throws EmailException, IOException {	
		if(file == null || file.isEmpty()) {
			file = null;
		}
		fotoService.inserir(usuario, file);
		adicionarImagemURI(usuario);
		Usuario usuariofoto = new Usuario();
		usuariofoto.setNome(usuario.getNome());
		usuariofoto.setSobrenome(usuario.getSobrenome());
		usuariofoto.setDataNascimento(usuario.getDataNascimento());
		usuariofoto.setEmail(usuario.getEmail());
		usuariofoto.setUrlImagem(usuario.getUrlImagem());
		
		
		return usuariofoto;
	}
}

