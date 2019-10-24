package br.com.casamento.vo.entrada;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.casamento.modelo.Grupo;

public class GrupoEntradaVO {
	
	@NotNull @NotBlank @Length(min = 5)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrupoEntradaVO() {

	}

	public GrupoEntradaVO(Grupo grupo) {
		this.descricao = grupo.getDescricao();
	}

	public Grupo converter(GrupoEntradaVO dadosEntradaVo) {

		Grupo grupo = new Grupo(dadosEntradaVo.getDescricao());
		return grupo;
	}
}
