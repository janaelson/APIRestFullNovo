package org.serratec.projetofinal.ApiRestful.repository;

import java.util.List;

import org.serratec.projetofinal.ApiRestful.model.Comentario;
import org.serratec.projetofinal.ApiRestful.model.Relacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	List<Comentario> findByPostagemId(Long postagemId);
	
	@Query("SELECT c FROM Comentario c WHERE c.postagem.id = :idcomentario")
	public List<Comentario> buscarComentarios(@Param("idcomentario") Long idcomentario );
}
