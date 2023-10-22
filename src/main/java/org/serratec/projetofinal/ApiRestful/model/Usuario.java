package org.serratec.projetofinal.ApiRestful.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 60)
	private String nome;

	@NotBlank(message = "Sobrenome é obrigatório")
	@Size(max = 60)
	private String sobrenome;

	@Email(message = "Email inválido")
	@Size(max = 60)
	private String email;

	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 4, max = 10, message = "A senha deve ter entre {min} e {max} caracteres")
	@Column(nullable = false, length = 10)
	private String senha;
	
	private String urlImagem;

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	@Column
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy = "id.usuarioseguido", cascade = CascadeType.ALL)
	private List<Relacionamento> relacionamento = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Relacionamento> getRelacionamento() {
		return relacionamento;
	}

	public void setRelacionamento(List<Relacionamento> relacionamento) {
		this.relacionamento = relacionamento;
	}

}
