package org.serratec.projetofinal.ApiRestful.controller;

import java.util.List;

import org.serratec.projetofinal.ApiRestful.DTO.ComentarioDTO;
import org.serratec.projetofinal.ApiRestful.model.Comentario;
import org.serratec.projetofinal.ApiRestful.repository.ComentarioRepository;
import org.serratec.projetofinal.ApiRestful.service.ComentarioService;
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
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> listarComentariosPorPostagem() {
        List<ComentarioDTO> comentarios = comentarioService.listarComentariosPorPostagem();
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> buscarComentarioPorId(@PathVariable Long id) {
        ComentarioDTO comentario = comentarioService.buscarComentarioPorId(id);
        if (comentario != null) {
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> inserirComentario(@RequestBody Comentario comentario) {
        ComentarioDTO comentarioDTO = comentarioService.inserirComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> atualizarComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        ComentarioDTO comentarioDTO = comentarioService.buscarComentarioPorId(id);
        if (comentario != null) {
        	comentario.setId(id);
        	comentarioRepository.save(comentario);
            return ResponseEntity.ok(comentarioDTO);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirComentario(@PathVariable Long id) {
        comentarioService.excluirComentario(id);
        return ResponseEntity.noContent().build();
    }
}
