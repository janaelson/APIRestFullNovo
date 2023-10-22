package org.serratec.projetofinal.ApiRestful.DTO;

import java.io.Serializable;
import java.util.Date;

public class ComentarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String texto;
    private Date dataCriacao;
    

    public ComentarioDTO() {
    }


	public ComentarioDTO(String texto, Date dataCriacao) {
		this.texto = texto;
		this.dataCriacao = dataCriacao;
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