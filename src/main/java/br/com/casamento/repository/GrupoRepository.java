package br.com.casamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.casamento.modelo.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	public Grupo findByDescricao(String descricao);
}
