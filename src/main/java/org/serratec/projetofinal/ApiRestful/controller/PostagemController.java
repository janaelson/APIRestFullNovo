package org.serratec.projetofinal.ApiRestful.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.projetofinal.ApiRestful.DTO.PostagemDTO;
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
    private PostagemService postagemService; 

    @GetMapping
    public ResponseEntity<List<PostagemDTO>> listar() {
        List<PostagemDTO> postagens = postagemService.listarPostagens();
        return ResponseEntity.ok(postagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
        PostagemDTO postagemDTO = postagemService.buscarPostagemPorId(id);
        if (postagemDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postagemDTO);
    }

    @PostMapping
    public ResponseEntity<PostagemDTO> inserir(@Valid @RequestBody PostagemDTO postagemDTO) {
        PostagemDTO novaPostagem = postagemService.inserirPostagem(postagemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPostagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PostagemDTO postagemDTO) {
        PostagemDTO postagemAtualizada = postagemService.atualizarPostagem(id, postagemDTO);
        if (postagemAtualizada != null) {
            return ResponseEntity.ok(postagemAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        postagemService.excluirPostagem(id);
        return ResponseEntity.noContent().build();
    }
}