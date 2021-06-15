package br.com.mudanceiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.Usuario;

@Repository
public interface MudanceiroRepository extends JpaRepository<Mudanceiro, Long>{

	Optional<Mudanceiro> findByUsuario(Usuario cliente);

}
