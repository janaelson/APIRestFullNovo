package org.serratec.projetofinal.ApiRestful.DTO;

import java.util.Date;
import java.util.List;

public class PostagemDTO {
    private Long id;
    private String conteudo;
    private Date dataCriacao;
    private UsuarioDTO usuario;
    private List<ComentarioDTO> comentarios;
    
    public PostagemDTO() {}

    public PostagemDTO(Long id, String conteudo, Date dataCriacao, UsuarioDTO usuario,
			List<ComentarioDTO> comentarios) {
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
		this.usuario = usuario;
		this.comentarios = comentarios;
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
