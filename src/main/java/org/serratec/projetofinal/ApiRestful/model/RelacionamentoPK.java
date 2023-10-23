package org.serratec.projetofinal.ApiRestful.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_usuario1")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_usuario2")
	private Usuario usuarioseguido;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioseguido() {
		return usuarioseguido;
	}

	public void setUsuarioseguido(Usuario usuarioseguido) {
		this.usuarioseguido = usuarioseguido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((usuarioseguido == null) ? 0 : usuarioseguido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelacionamentoPK other = (RelacionamentoPK) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (usuarioseguido == null) {
			if (other.usuarioseguido != null)
				return false;
		} else if (!usuarioseguido.equals(other.usuarioseguido))
			return false;
		return true;
	}

}
