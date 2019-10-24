package br.com.casamento.vo.entrada;

import br.com.casamento.modelo.Grupo;

public class GrupoEntradaVO {

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
