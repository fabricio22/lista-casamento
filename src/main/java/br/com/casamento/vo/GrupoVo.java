package br.com.casamento.vo;

import br.com.casamento.modelo.Grupo;

public class GrupoVo {

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GrupoVo() {

	}

	public GrupoVo(Grupo grupo) {
		this.descricao = grupo.getDescricao();
	}

	public Grupo converter(GrupoVo dadosEntradaVo) {

		Grupo grupo = new Grupo(dadosEntradaVo.getDescricao());
		return grupo;
	}
}
