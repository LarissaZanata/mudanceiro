package br.com.mudanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mudanceiro.model.Mudanceiro;

@Repository
public interface MudanceiroRepository extends JpaRepository<Mudanceiro, Long>{

}
