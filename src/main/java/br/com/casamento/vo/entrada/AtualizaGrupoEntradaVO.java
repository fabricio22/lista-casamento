package br.com.casamento.vo.entrada;

import javax.validation.constraints.NotNull;

import br.com.casamento.modelo.Grupo;
import br.com.casamento.repository.GrupoRepository;

public class AtualizaGrupoEntradaVO {
	
	@NotNull
	private String descricao;
	
	public AtualizaGrupoEntradaVO() {
		
	}
	
	public AtualizaGrupoEntradaVO(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
		
	public Grupo atualizar(Long id, GrupoRepository grupoRepository) {
		Grupo grupo = grupoRepository.getOne(id);
		grupo.setDescricao(this.descricao);
		return grupo;
	}

}
