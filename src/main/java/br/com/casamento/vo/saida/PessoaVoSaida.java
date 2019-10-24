package br.com.casamento.vo.saida;

import br.com.casamento.modelo.Pessoa;

public class PessoaVoSaida {
	
	private String nome;
	private String nomeGrupo;
	private Long numeroMesa;
	

	public PessoaVoSaida() {
		
	}
	
	public PessoaVoSaida(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.nomeGrupo = pessoa.getGrupo().getDescricao();
		this.numeroMesa = pessoa.getMesa().getId();
	}

	public String getNome() {
		return nome;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}
	
	public Long getNumeroMesa() {
		return numeroMesa;
	}

}
