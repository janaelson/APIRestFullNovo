package org.serratec.projetofinal.ApiRestful.DTO;

import java.io.Serializable;
import java.util.Date;

import org.serratec.projetofinal.ApiRestful.model.Comentario;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String texto;
	private Date dataCriacao;

	public ComentarioDTO() {
	}

	public ComentarioDTO(Comentario comentario) {
		this.texto = comentario.getTexto();
		this.dataCriacao = comentario.getDataCriacao();
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}