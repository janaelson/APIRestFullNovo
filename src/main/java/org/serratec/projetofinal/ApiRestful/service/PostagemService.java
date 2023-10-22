package org.serratec.projetofinal.ApiRestful.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.projetofinal.ApiRestful.DTO.ComentarioDTO;
import org.serratec.projetofinal.ApiRestful.DTO.PostagemDTO;
import org.serratec.projetofinal.ApiRestful.DTO.UsuarioDTO;
import org.serratec.projetofinal.ApiRestful.model.Postagem;
import org.serratec.projetofinal.ApiRestful.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    public List<PostagemDTO> listarPostagens() {
        List<Postagem> postagens = postagemRepository.findAll();
        return postagens.stream()
            .map(this::postagemParaPostagemDTO)
            .collect(Collectors.toList());
    }

    public PostagemDTO buscarPostagemPorId(Long id) {
        Optional<Postagem> postagemOpt = postagemRepository.findById(id);
        if (postagemOpt.isPresent()) {
            Postagem postagem = postagemOpt.get();
            return postagemParaPostagemDTO(postagem);
        }
        return null;
    }

    public PostagemDTO inserirPostagem(PostagemDTO postagemDTO) {
        Postagem postagem = new Postagem();
        postagem.setConteudo(postagemDTO.getConteudo());
        
        postagem = postagemRepository.save(postagem);
        return postagemParaPostagemDTO(postagem);
    }

    public PostagemDTO atualizarPostagem(Long id, PostagemDTO postagemDTO) {
        Optional<Postagem> postagemOpt = postagemRepository.findById(id);
        if (postagemOpt.isPresent()) {
            Postagem postagem = postagemOpt.get();
            postagem.setConteudo(postagemDTO.getConteudo());
            
            postagem = postagemRepository.save(postagem);
            return postagemParaPostagemDTO(postagem);
        }
        return null;
    }

    public void excluirPostagem(Long id) {
        postagemRepository.deleteById(id);
    }

    private PostagemDTO postagemParaPostagemDTO(Postagem postagem) {
        PostagemDTO postagemDTO = new PostagemDTO();
        postagemDTO.setId(postagem.getId());
        postagemDTO.setConteudo(postagem.getConteudo());
        postagemDTO.setDataCriacao(postagem.getDataCriacao());

        if (postagem.getUsuario() != null) {
            Long userId = postagem.getUsuario().getId();
            UsuarioDTO usuarioDTO = usuarioService.findById(userId); 
            postagemDTO.setUsuario(usuarioDTO);
        }

        List<ComentarioDTO> comentarios = comentarioService.listarComentariosPorPostagem(postagem.getId());
        postagemDTO.setComentarios(comentarios);

        return postagemDTO;
}
}
