package br.com.casamento.vo.saida;

import br.com.casamento.modelo.Pessoa;

public class PessoaSaidaVO {
	
	private String nome;
	private String nomeGrupo;
	private Long numeroMesa;
	

	public PessoaSaidaVO() {
		
	}
	
	public PessoaSaidaVO(Pessoa pessoa) {
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
