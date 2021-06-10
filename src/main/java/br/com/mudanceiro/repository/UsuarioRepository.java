package br.com.mudanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mudanceiro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
