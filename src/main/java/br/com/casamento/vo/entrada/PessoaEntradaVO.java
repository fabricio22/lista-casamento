package br.com.casamento.vo.entrada;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.casamento.modelo.Grupo;
import br.com.casamento.modelo.Mesa;
import br.com.casamento.modelo.Pessoa;

public class PessoaEntradaVO {
	
	@NotNull @NotBlank @Length(min = 15)
	private String nome;
	@NotNull
	private Long codigoGrupo;
	@NotNull
	private Long numeroMesa;

	public String getNome() {
		return nome;
	}
	
	public Long getCodigoGrupo() {
		return codigoGrupo;
	}
	
	public Long getNumeroMesa() {
		return numeroMesa;
	}
	
	public PessoaEntradaVO() {
		
	}
	
	public PessoaEntradaVO(Pessoa pessoa) {
		this.nome = pessoa.getNome();
	}

	public Pessoa converter(PessoaEntradaVO dadosEntradaVo, Grupo grupo, Mesa mesa) {

		Pessoa pessoa = new Pessoa(dadosEntradaVo.getNome(), grupo, mesa);
		return pessoa;
	}

	@Override
	public String toString() {
		return "PessoaVo [nome=" + nome + ", codigoGrupo=" + codigoGrupo + "]";
	}
	
	
}
