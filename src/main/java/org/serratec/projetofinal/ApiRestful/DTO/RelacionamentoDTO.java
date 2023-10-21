package org.serratec.projetofinal.ApiRestful.DTO;

import java.time.LocalDate;

import org.serratec.projetofinal.ApiRestful.model.Relacionamento;

public class RelacionamentoDTO {

	private UsuarioDTO usuarioDTO;

	private LocalDate dataInicio;

	public RelacionamentoDTO() {
		super();
	}

	public RelacionamentoDTO(Relacionamento relacionamento) {
		super();
		this.usuarioDTO = new UsuarioDTO (relacionamento.getId().getUsuarioseguido());
		this.dataInicio = relacionamento.getDataInicio();
		
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Long getId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
