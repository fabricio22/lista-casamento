package br.com.casamento.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.casamento.modelo.Pessoa;

public class PessoaVo {
	
	@NotNull @NotBlank @Length(min = 15)
	private String nome;

	public String getNome() {
		return nome;
	}

	public PessoaVo() {
		
	}
	
	public PessoaVo(Pessoa pessoa) {
		this.nome = pessoa.getNome();
	}

	public Pessoa converter(PessoaVo dadosEntradaVo) {

		Pessoa pessoa = new Pessoa(dadosEntradaVo.getNome());
		return pessoa;
	}

}
