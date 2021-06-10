package br.com.mudanceiro.service;

import java.util.List;

import br.com.mudanceiro.model.Usuario;

public interface UsuarioService {

	public Usuario getUsuarioById(Long id);
	public Usuario save(Usuario usuario);
	public void delete(Long id);
	public void update(Long id, Usuario usuario);
	public List<Usuario> find(Usuario filtro);
}
