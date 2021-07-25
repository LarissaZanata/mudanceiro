package br.com.mudanceiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import br.com.mudanceiro.controller.form.UsuarioForm;
import br.com.mudanceiro.controller.form.UsuarioMudanceiroForm;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{campo.email.obrigatorio}")
	private String email;
	
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	@NotEmpty(message = "{campo.telefone.obrigatorio}")
	private String telefone;
	
	private boolean mudanceiro;
	
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

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
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isMudanceiro() {
		return mudanceiro;
	}

	public void setMudanceiro(boolean mudanceiro) {
		this.mudanceiro = mudanceiro;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	public static Usuario converte(UsuarioForm usuarioForm) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioForm.getNome());
		usuario.setEmail(usuarioForm.getEmail());
		usuario.setMudanceiro(false);
		usuario.setSenha(usuarioForm.getSenha());
		usuario.setTelefone(usuarioForm.getTelefone());
		return usuario;
	}
	
	public static Usuario converteMudanceiro(UsuarioMudanceiroForm usuarioForm) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioForm.getNome());
		usuario.setEmail(usuarioForm.getEmail());
		usuario.setMudanceiro(true);
		usuario.setSenha(usuarioForm.getSenha());
		usuario.setTelefone(usuarioForm.getTelefone());
		usuario.setTipoServico(usuarioForm.getTipoServico());
		return usuario;
	}
}
