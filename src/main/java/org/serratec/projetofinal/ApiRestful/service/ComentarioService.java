package org.serratec.projetofinal.ApiRestful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.serratec.projetofinal.ApiRestful.DTO.ComentarioDTO;
import org.serratec.projetofinal.ApiRestful.model.Comentario;
import org.serratec.projetofinal.ApiRestful.repository.ComentarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<ComentarioDTO> listarComentariosPorPostagem() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream()
            .map(this::comentarioParaComentarioDTO)
            .collect(Collectors.toList());
    }

    public ComentarioDTO buscarComentarioPorId(Long id) {
        Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
        return comentarioOpt.map(this::comentarioParaComentarioDTO).orElse(null);
    }

    public ComentarioDTO inserirComentario(Comentario comentario) {
    	ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setTexto(comentario.getTexto());
        comentarioDTO.setDataCriacao(comentario.getDataCriacao());

        comentarioRepository.save(comentario);
        return comentarioDTO; 

    }

    public ComentarioDTO atualizarComentario(Long id, String novoTexto) {
        Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
        if (comentarioOpt.isPresent()) {
            Comentario comentario = comentarioOpt.get();
            comentario.setTexto(novoTexto);
            comentario.setDataCriacao(new Date()); 
            comentario = comentarioRepository.save(comentario);
            return comentarioParaComentarioDTO(comentario);
        }
        return null;
    }

    public void excluirComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO comentarioParaComentarioDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setTexto(comentario.getTexto());
        comentarioDTO.setDataCriacao(comentario.getDataCriacao());

        return comentarioDTO;
    }
}
