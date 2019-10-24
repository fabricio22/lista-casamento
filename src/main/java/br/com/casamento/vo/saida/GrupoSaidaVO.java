package br.com.casamento.vo.saida;

import org.hibernate.annotations.NotFound;

import br.com.casamento.modelo.Grupo;

public class GrupoSaidaVO {

	@NotFound
	private Long id;
	private String descricao;
	
	public GrupoSaidaVO() {
		
	}
	
	public GrupoSaidaVO(Grupo grupo) {
		this.id = grupo.getId();
		this.descricao = grupo.getDescricao();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}	
}
