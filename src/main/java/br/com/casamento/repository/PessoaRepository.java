package br.com.casamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.casamento.modelo.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	public Pessoa findByNome(String nome);

}
