package org.serratec.projetofinal.ApiRestful.DTO;

import java.util.Date;
import java.util.List;

import org.serratec.projetofinal.ApiRestful.model.Postagem;
import org.serratec.projetofinal.ApiRestful.model.Usuario;

public class PostagemDTO {
	private Long id;
	private String conteudo;
	private Date dataCriacao;
	private UsuarioDTO usuario;
	private List<ComentarioDTO> comentarios;

	public PostagemDTO() {
	}

	public PostagemDTO(Postagem postagem, Usuario usuario) {
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
		this.dataCriacao = postagem.getDataCriacao();
		this.usuario = new UsuarioDTO(postagem.getUsuario());
		this.usuario.setId(postagem.getUsuario().getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}
}